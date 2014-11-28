package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

public class Server implements Runnable{
	final static public int WAITINGROOM = 1;
	final static public int INGAME = 2;
	final static public int GAMEOVER = 3;
	
	private ReentrantLock lock = new ReentrantLock();
	private Vector<ServerThread> playerThreads;
	private Vector<ChatThread> ctVector = new Vector<ChatThread>();
	private Vector<Widget> currentWidgets;
	private ServerSocket serversocket;
	private int gamestate;
	private int currentLevel;
	private int currentPoints;
	private int currentMisses;
	private Random generator;

	public Server(){
		playerThreads = new Vector<ServerThread>(4);
		currentWidgets = new Vector<Widget>();
		gamestate = Server.WAITINGROOM;
		generator = new Random();
		try {
			this.serversocket = new ServerSocket(55555);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		lock.unlock();
	}
	
	public int getState(){
		return gamestate;
	}
	
	public Widget getInstruction(){
		lock.lock();
		//This line generates a random widget from the list of widgets, and then
		//Uses the widgets getRandomInstruction to generate a new random widget to use
		Widget w = currentWidgets.get(generator.nextInt(currentWidgets.size())).getRandomInstruction();
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
		for (ServerThread st: playerThreads){
			if (w == st.getInstruction()){
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
		return 10 - currentLevel;
	}
	
	//Time each instruction gets
	public int getTime(){
		return 3000 - 60 * currentLevel;
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
		if (currentMisses >= getMaxMisses()){
			gameOver();
		}
		lock.unlock();
	}
	
	private void startLevel(int i){
		currentLevel = i;
		currentPoints = 0;
		currentMisses = 0;
		currentWidgets.clear();
		for (ServerThread s : playerThreads){
			s.startLevel(i);
		}
	}
	
	private void gameOver(){
		for (ServerThread s : playerThreads){
			s.gameOver();
		}
	}
	
	public ReentrantLock getLock(){
		return lock;
	}
	
	public void sendMessage(String message, ChatThread ct) {
		for(ChatThread c : ctVector) {
			c.send(message);
		}
	}
	
	public void removeChatThread(ChatThread ct) {
		ctVector.remove(ct);
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
				s.sendMessage(msg);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
