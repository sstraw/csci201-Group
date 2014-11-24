package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Server implements Runnable{
	private ReentrantLock lock = new ReentrantLock();
	private Condition newLevel = lock.newCondition();
	private Vector<ServerThread> playerThreads;
	private ServerSocket serversocket;

	public Server(){
		playerThreads = new Vector<ServerThread>(4);
		try {
			this.serversocket = new ServerSocket(55555);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addWidgets(Vector <Widget> widgets){
		
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
	
	public void playerReady(ServerThread s, Boolean b){
		//Sets a player value as ready -- How? Going to have to look into implementations to tell everyone to start
	}
	
	public ReentrantLock getLock(){
		return lock;
	}
	
	public void run() {
		//Main server loop to receive new connections?
		while (true){
			try {
				Socket s = serversocket.accept();
				if (playerThreads.size() < 4){
					playerThreads.add(new ServerThread(this, s));
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
