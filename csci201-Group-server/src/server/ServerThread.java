package server;

import java.net.Socket;

public class ServerThread implements Runnable {
	private Socket socket;
	private Server server;
	
	public ServerThread(Server server, Socket socket){
		this.socket = socket;
		this.server = server;
	}
	
	public void instructionCompleted(){
		
	}
	
	public void newLevel(){
		//Trigger called when a new level is started
	}
	
	public void run(){
		
	}
	
	
}
