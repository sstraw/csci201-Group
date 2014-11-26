package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class ServerThread implements Runnable {
	private Socket socket;
	private Server server;
	private BufferedReader buffer;
	private ObjectInputStream object;
	private PrintWriter printwrite;
	private String name;
	private boolean ready;
	private boolean isRunning;
	
	public ServerThread(Server server, Socket socket){
		this.socket = socket;
		this.server = server;
		try {
			buffer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			printwrite = new PrintWriter(this.socket.getOutputStream());
			object = new ObjectInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isRunning = true;
		ready = false;
	}
	
	public void instructionCompleted(){
		//Server calls this when an instruction is passed
		//Needs to get a new instruction from the server, and pass it to the player
	}
	
	public void newLevel(){
		//Trigger called when a new level is started
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isReady(){
		return ready;
	}
	
	public void startLevel(int levelnumber){
		//Notify starting a new level
		printwrite.println("startLevel");
		printwrite.print(levelnumber);
	}
	
	public void run(){

		while (isRunning){
			//Receive and process input
			try {
				String value1 = buffer.readLine().trim();
				String value2;
				
				//Switch values
				switch(value1){
				
				case("setState"):
					value2 = buffer.readLine().trim();
					switch(value2){
					case("ready"):
						this.ready = true;
						this.server.playerReady();
						break;
					case("notready"):
						this.ready = false;
						break;
					default:
						//Do nothing.
					}
					
				case("setName"):
					value2 = buffer.readLine().trim();
					this.name = value2;

				case("giveWidgets"):
					Object o = object.readObject();
					if (o instanceof Vector<?>){
						Vector<Widget> widgets = (Vector<Widget>) o;
						this.server.addWidgets(widgets);
					}
					else{
						System.out.println("ERR:NOT RECEVING CORRECT WIDGET");
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}

