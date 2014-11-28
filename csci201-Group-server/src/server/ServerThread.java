package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

public class ServerThread implements Runnable {
	private Socket socket;
	private Server server;
	private BufferedReader buffer;
	private ObjectInputStream objectin;
	private ObjectOutputStream objectcannon;
	private PrintWriter printwrite;
	private String name;
	private boolean ready;
	private boolean isRunning;
	private Widget instruction;
	private int instructions_completed;
	private ReentrantLock lock;
	private Thread timer;
	private Thread thread;
	
	public ServerThread(Server server, Socket socket){
		this.socket = socket;
		this.server = server;
		lock = new ReentrantLock();
		try {
			buffer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			printwrite = new PrintWriter(this.socket.getOutputStream());
			objectin = new ObjectInputStream(this.socket.getInputStream());
			objectcannon = new ObjectOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isRunning = true;
		ready = false;
		this.instructions_completed = 0;
		this.thread = new Thread(this);
		thread.start();
	}
	
	public void givePoint(){
		//Adds a point for an instruction completed
		lock.lock();
		this.instructions_completed++;
		lock.unlock();
	}
	
	public int getPoints(){
		return instructions_completed;
	}
	
	public void sendMessage(String msg){
		lock.lock();
		printwrite.println("message");
		printwrite.println(msg);
		lock.unlock();
	}
	
	public void instructionCompleted(){
		//Server calls this when an instruction is passed
		//Needs to get a new instruction from the server, and pass it to the player
		lock.lock();
		timer.interrupt();
		printwrite.println("instruction completed");
		this.giveInstruction();
		lock.unlock();
	}
	
	//Called by the timer thread when there is no more time
	public void timerDone(){
		lock.lock();
		this.server.instructionFailed();
		printwrite.println("instruction failed");
		this.giveInstruction();
		lock.unlock();
	}
	
	public void gameOver(){
		lock.lock();
		printwrite.println("game over");
		thread.interrupt();
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	public Widget getInstruction(){
		return instruction;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isReady(){
		return ready;
	}
	
	
	public void startLevel(int levelnumber){
		//Notify starting a new level
		lock.lock();
		printwrite.println("startLevel");
		printwrite.print(levelnumber);
		lock.unlock();
	}
	
	private void giveInstruction(){
		instruction = this.server.getInstruction();
		int timeout = this.server.getTime();
		try {
			objectcannon.writeObject(instruction);
			printwrite.print(timeout);
			timer = new Thread(new Timer(this, timeout));
			timer.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					break;
					
				case("setName"):
					value2 = buffer.readLine().trim();
					this.name = value2;
					break;

				case("giveWidgets"):
					Object o = objectin.readObject();
					if (o instanceof Vector<?>){
						Vector<?> widgets = (Vector<?>) o;
						this.server.addWidgetsFromNetwork(widgets);
					}
					else{
						System.out.println("ERR:NOT RECEVING CORRECT WIDGET FORMAT");
					}
					break;
					
				case("message"):
					value2 = buffer.readLine().trim();
					this.server.sendMessage(this, value2);
					break;
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

class Timer implements Runnable{
	private ServerThread serverthread;
	private int milliseconds;
	
	public Timer(ServerThread serverthread, int milliseconds){
		this.serverthread = serverthread;
	}
	
	public void run(){
		try {
			Thread.sleep(milliseconds);
			serverthread.timerDone();
		} catch (InterruptedException e) {
			//No error here because the ServerThread may kill this when it's no longer needed;
		}
	}
}