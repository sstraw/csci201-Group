package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

import client.Widget;

public class Server implements Runnable{
	final static public int WAITINGROOM = 1;
	final static public int INGAME = 2;
	final static public int GAMEOVER = 3;
	
	private ReentrantLock lock = new ReentrantLock();
	private Vector<ServerThread> playerThreads;
	private Vector<ChatThread> ctVector = new Vector<ChatThread>();
	private Vector<Widget> currentWidgets;
	private Vector<Integer> dashboardIndexes;
	private ServerSocket serversocket;
	private int gamestate;
	private int currentLevel;
	private int currentPoints;
	private int currentMisses;
	private Random generator;
	private Thread thread;
	private int lvlsStarted = 0;
	
	public Server(){
		playerThreads = new Vector<ServerThread>(4);
		currentWidgets = new Vector<Widget>();
		gamestate = Server.WAITINGROOM;
		generator = new Random();
		try {
			this.serversocket = new ServerSocket(55555);
		} catch (IOException e) {
			e.printStackTrace();
		}
		thread = new Thread(this);
		thread.start();
	}
	
	public static void main(String[] args){
		Server s = new Server();
		s.run();
	}
	
	//Because it's over the network, need to check first everything is a widget before adding
	public void addWidgetsFromNetwork(Vector <?> widgets){
		lock.lock();
		for (Object o : widgets){
			if (o instanceof Widget){
				currentWidgets.addElement((Widget) o);
			}
		}
		lvlsStarted++;
		if (lvlsStarted == playerThreads.size()) {
			for (ServerThread s : playerThreads){
				s.giveInstruction();
			}
		}
		lock.unlock();
	}
	
	public int getState(){
		return gamestate;
	}
	
	public Widget getInstruction(){
		lock.lock();
		//This line generates a random widget from the list of widgets, and then
		//Uses the widgets getRandomInstruction to generate a new random widget to use
		boolean valid = false;
		Widget w = currentWidgets.get(generator.nextInt(currentWidgets.size())).getRandomInstruction();
		while(!valid){
			valid = true;
			for (ServerThread s : playerThreads){
				if (w.equals(s.getInstruction())){
					valid = false;
					w = currentWidgets.get(generator.nextInt(currentWidgets.size())).getRandomInstruction();
					break;
				};
			}
		}
		lock.unlock();
		return w;
	}
	
	public void widgetChanged(ServerThread s, Widget w){
		//Notifies the server the widget was changed.
		//The server should go through the server threads,
		//Check to see if any of the instructions match the widget change
		//And notify the server thread if that is the case
		//Also notifies all threads if the level is completed
		lock.lock();
		for(Widget w2 : currentWidgets){
			if (w2.getName().equals(w.getName())){
				w2.update(w);
				break;
			}
		}
		for (ServerThread st: playerThreads){
			if (w.equals(st.getInstruction())){
				s.givePoint();
				st.givePoint();
				this.instructionCompleted(st);
				break;
			}
		}
		lock.unlock();
	}
	
	public void playerReady(){
		this.lock.lock();
		for (ServerThread s : playerThreads){			
			//Leave if one of the threads not ready
			if (!s.isReady()){
				this.lock.unlock();
				return;
			}
		}
		startLevel(1);
		this.lock.unlock();
	}
		
	//Returns how many points needed to "win" the level. Not sure how we want to do it yet so...
	private int getLevelCap(){
		return 10 * currentLevel;
	}
	
	private int getMaxMisses(){
		return 6 - currentLevel;
	}
	
	//Time each instruction gets
	public int getTime(){
		return 10000 - 60 * currentLevel;
	}
	
	private void instructionCompleted(ServerThread s){
		currentPoints++;
		if (currentPoints >= getLevelCap() && currentLevel != 5){
			currentLevel++;
			startLevel(currentLevel);
		}
		else{
			s.instructionCompleted();
		}
	}
	
	public void instructionFailed(){
		lock.lock();
		currentMisses++;
		lock.unlock();
	}
	
	public boolean failLimit() {
		lock.lock();
		if (currentMisses >= getMaxMisses()) {
			lock.unlock();
			return true;
		}
		lock.unlock();
		return false;
	}
	
	private void startLevel(int i){
		currentLevel = i;
		currentPoints = 0;
		currentMisses = 0;
		currentWidgets.clear();
		dashboardIndexes = new Vector<Integer>(Arrays.asList(1, 2, 3, 4));
		Collections.shuffle(dashboardIndexes);
		lvlsStarted=0;
		for (ServerThread s : playerThreads){
			s.startLevel(i);
		}
	}
	
	public void incrementStartedLvls() {
		lock.lock();
		lvlsStarted++;
		if (lvlsStarted == playerThreads.size()) {
			for (ServerThread s : playerThreads){
				s.giveInstruction();
			}
		}
		lock.unlock();
	}
	
	public void gameOver() {
		lock.lock();
		Vector<String> usernames = new Vector<String>();
		Vector<String> scores = new Vector<String>();

		for (int i=0; i<playerThreads.size(); i++) {
			usernames.add(playerThreads.get(i).getName());
			scores.add("" + playerThreads.get(i).getPoints());
		}
		for (ServerThread s : playerThreads) {	
			s.storeNumUsers(playerThreads.size());
			s.sendScores(usernames, scores);
			s.gameOver();	
		}
		lock.unlock();
	}
	
	public ReentrantLock getLock(){
		return lock;
	}
	
	public int getLevel(){
		return currentLevel;
	}
	
	public int getDashboardIndex(){
		return dashboardIndexes.remove(0);
	}
	
	public void sendMessage(String message, ChatThread ct) {
		for(ChatThread c : ctVector) {
			c.send(message);
		}
	}
	
	public void removeChatThread(ChatThread ct) {
		ctVector.remove(ct);
	}
	
	public void getPlayers(ServerThread caller){
		lock.lock();
		for (ServerThread s : playerThreads){
			if (!s.equals(caller)){
				caller.newConnection(s.getName());
				s.newConnection(caller.getName());
			}
		}
		lock.unlock();
	}
	
	public void sendMessage(ServerThread serverthread, String msg){
		this.lock.lock();
		if (msg.startsWith("/")){
			//Indicates some sort of command
			if (msg.startsWith("/msg") && msg.contains(" ")){
				String player = msg.split(" ")[1];
				for (ServerThread s : playerThreads){
					if (s.getName().equals(player)){
						msg = msg.substring(msg.indexOf(" ")+1);
						if (msg.contains(" ")){
							//Has a message following, extract
							msg = serverthread.getName() + " says <private>: " + msg.substring(msg.indexOf(" ")+1);
							s.sendMessage(msg);
							this.lock.unlock();
							return;
						}
					}
				}
			}
			serverthread.sendMessage("Err: Message not formatted correctly");
		}
		else{
			for (ServerThread s : playerThreads){
				s.sendMessage(serverthread.getName() + "> " + msg);
			}
		}
		this.lock.unlock();
	}
	
	public void run() {
		//Main server loop to receive new connections?
		while (true){
			try {
				Socket s = serversocket.accept();
				if (playerThreads.size() < 4){
					playerThreads.add(new ServerThread(this, s));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
