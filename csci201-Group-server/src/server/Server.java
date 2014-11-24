package server;

import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

public class Server implements Runnable{
	private ReentrantLock lock = new ReentrantLock();
	private Vector<ServerThread> playerThreads;

	public Server(){
		playerThreads = new Vector<ServerThread>(4);
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
		//Also notifies the thread if 
	}
	
	public void run() {
		//Main server loop to receive new connections?
		
	}
	
}
