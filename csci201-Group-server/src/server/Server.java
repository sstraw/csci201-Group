package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Server implements Runnable{
	final static public int WAITINGROOM = 1;
	final static public int INGAME = 2;
	final static public int GAMEOVER = 3;
	
	private ReentrantLock lock = new ReentrantLock();
	private Condition newLevel = lock.newCondition();
	private Vector<ServerThread> playerThreads;
	private Vector<ChatThread> ctVector = new Vector<ChatThread>();
	private ServerSocket serversocket;
	private int gamestate;
	private Socket s;
	private int currentLevel;
	private int currentPoints;

	public Server(){
		playerThreads = new Vector<ServerThread>(4);
		gamestate = Server.WAITINGROOM;
		try {
			this.serversocket = new ServerSocket(55555);
			
			// My test chat server had this code here instead of in run,
			// so if it doesn't work in run(), we can try uncommenting this.
			/*while(true){
				System.out.println("Waiting for connections...");
				s = serversocket.accept();
				System.out.println("Connection from " + s.getInetAddress());
				if(s != null){
					ChatThread ct = new ChatThread(s, this);
					ctVector.add(ct);
					ct.start();
				}
			}*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addWidgets(Vector <Widget> widgets){
		
	}
	
	public int getState(){
		return gamestate;
	}
	
	public Widget getInstruction(){
		lock.lock();
		//Choose new widget
		lock.unlock();
		return new Widget(5, 5, 5, 5, "Default");
	}
	
	public void widgetChanged(ServerThread s, Widget w){
		//Notifies the server the widget was changed.
		//The server should go through the server threads,
		//Check to see if any of the instructions match the widget change
		//And notify the server thread if that is the case
		//Also notifies all threads if the level is completed
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
	
	public void instructionCompleted(){
		currentPoints++;
		if (currentPoints >= getLevelCap()){
			currentLevel++;
			startLevel(currentLevel);
		}
	}
	
	private void startLevel(int i){
		currentLevel = i;
		currentPoints = 0;
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
	
	public void run() {
		//Main server loop to receive new connections?
		while (true){
			try {
				Socket s = serversocket.accept();
				if (playerThreads.size() < 4){
					playerThreads.add(new ServerThread(this, s));
				}
				// If ChatThread doesn't work here, uncomment the code in the constructor
				if(s != null){
					ChatThread ct = new ChatThread(s, this);
					ctVector.add(ct);
					ct.start();
				}
				else{
					s.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
