package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {
	private Socket socket;
	private Server server;
	private BufferedReader buffer;
	private PrintWriter printwrite;
	
	public ServerThread(Server server, Socket socket){
		this.socket = socket;
		this.server = server;
		try {
			buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printwrite = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void instructionCompleted(){
		//Server calls this when an instruction is passed
		//Needs to get a new instruction from the server, and pass it to the player
	}
	
	public void newLevel(){
		//Trigger called when a new level is started
	}
	
	public void run(){
		//Steps to complete:
		
		//1: Wait for ready
		
		while (server.getState() == Server.WAITINGROOM){
			//Check read in buffer
		}
		
		//Game loop:
			//2: Prepare for level
				//Aggregate widgets and pass to Server
			//4: Level loop
				//Listen for widget changes, pass up to server. Break loop on newLevel()
			//4: Close out level
				//Is there anything that needs to be done here?
		
		//Do we want to repeat?
		
	}
	
	
}

