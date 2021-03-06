package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import client.Widget;

public class ServerThread implements Runnable {
	private Socket socket;
	private Server server;
	private BufferedReader buffer;
	private ObjectInputStream objectin;
	private ObjectOutputStream objectcannon;
	private PrintWriter printwrite;
	private String name;
	private String avatar = "";
	private boolean ready;
	private boolean isRunning;
	private Widget instruction;
	private int instructions_completed;
	private ReentrantLock lock = new ReentrantLock();;
	private Condition receivingWidgets = lock.newCondition();
	private Thread timer;
	private Thread thread;
	private int numUsers;
	
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
	
	public String getAvatar(){
		return avatar;
	}
	
	public void sendInGameMessage(String msg){
		lock.lock();
		try{
			objectcannon.writeObject(new String("gameMessage"));
			objectcannon.writeObject(new String(msg));
		} catch (IOException e){
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	public void sendWaitingRoomMessage(String msg){
		lock.lock();
		try{
			objectcannon.writeObject(new String("waitingRoomMessage"));
			objectcannon.writeObject(new String(msg));
		} catch (IOException e){
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	public void storeNumUsers(int n) {
		numUsers=n;
	}
	
	public void sendScores(Vector<String> names, Vector<String> scores) {
		lock.lock();
		try {
			objectcannon.writeObject(new String("scores"));
			objectcannon.writeObject(new Integer(numUsers));
			System.out.println("Sending " + name + " " + numUsers + " users/scores");
			for (int i=0; i<numUsers; i++) {
				objectcannon.writeObject(new String(names.get(i)));
				objectcannon.writeObject(new String(scores.get(i)));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}

	public void instructionCompleted(){
		//Server calls this when an instruction is passed
		//Needs to get a new instruction from the server, and pass it to the player
		lock.lock();
		timer.interrupt();
		try {
			objectcannon.writeObject(new String("instruction completed"));
			this.giveInstruction();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	//Called by the timer thread when there is no more time
	public void timerDone(){
		lock.lock();
		this.server.instructionFailed();
		try {
			if (this.server.failLimit()) {
				this.server.gameOver();
			}
			else {
				objectcannon.writeObject(new String("instruction failed"));
				this.giveInstruction();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public void gameOver(){
		lock.lock();
		try {
			objectcannon.writeObject(new String("game over"));
			thread.interrupt();
			//socket.close();		//causing all the exceptions on gameover
			//want to keep socket open for scoring stuff
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
		if (timer!=null) {
			timer.interrupt();
		}
		try {
			objectcannon.writeObject(new String("startLevel"));
			objectcannon.writeObject(server.getLevel()); //level: should be based on Server variable
			objectcannon.writeObject(server.getDashboardIndex());	//index: should be a randomly generate index
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
		
		//waits for Widget vector to be returned
		
	/*	try {
			receivingWidgets.await();
			//String value = buffer.readLine().trim();
			//if (value.equals("giveWidgets")) {
				Object o = objectin.readObject();
				if (o instanceof Vector) {
					Vector v = (Vector) o;
					System.out.println("object read");
					//this.server.addWidgetsFromNetwork(v); //sends vector to be stored in Server
				}
			//}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		*/
		//giveInstruction();
	}
	
	public void giveInstruction(){
		lock.lock();
		instruction = this.server.getInstruction();
		//System.out.println("check");
		int timeout = this.server.getTime();
		try {
			objectcannon.writeObject(new String("instruction"));
			objectcannon.writeObject(instruction);
			objectcannon.writeObject(timeout);
			timer = new Thread(new Timer(this, timeout));
			timer.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public void newConnection(String s, String avatar){
		lock.lock();
		try {
			objectcannon.writeObject(new String("connected user"));
			System.out.println(name + " being sent connected user: " + s);
			objectcannon.writeObject(s);
			objectcannon.writeObject(avatar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public void setAvatar(String avatar){
		lock.lock();
		try {
			this.avatar = avatar;
			objectcannon.writeObject(new String("avatar"));
			objectcannon.writeObject(this.avatar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public void setReadyStatus(ServerThread otherPlayer){
		lock.lock();
		try{
			objectcannon.writeObject(new String("setReady"));
			String readyStatus = "";
			if(otherPlayer.isReady())
				readyStatus = "ready";
			else
				readyStatus = "notready";
			objectcannon.writeObject(readyStatus);
			objectcannon.writeObject(new String(otherPlayer.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public void run(){

		while (isRunning){
			//Receive and process input
			try {
				System.out.println("0 - Indicating...");
				String value1 = ((String) objectin.readObject()).trim();
				System.out.println("0 - Indicated");
				String value2;				
				
				//Switch values
				switch(value1){
				
				case("setState"):
					System.out.println("3 - State receiving");
					value2 = ((String) objectin.readObject()).trim();
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
					server.setReady(this);
					System.out.println("3 - State receivied");
					break;
					
				case("setName"):
					value2 = ((String) objectin.readObject()).trim();
					this.name = value2;
					server.getPlayers(this);
					break;
					
				case("avatar"):
					server.getAvatar(this);
					break;
					
				case("giveWidgets"):
					try {
						System.out.println("2 - Vector receiving");
						Object o = objectin.readObject();
						if (o instanceof Vector) {
							Vector v = (Vector) o;
							this.server.addWidgetsFromNetwork(v); //sends vector to be stored in Server
						}
						System.out.println("2 - Vector received");
					} catch (ClassNotFoundException cnfe) {
						cnfe.printStackTrace();
					}
					break; 
					
				case("widgetChanged"):
					try {
						System.out.println("1 - Widget Receiving");
						Object o = objectin.readObject();
						if (o instanceof Widget) {
							Widget w = (Widget) o;
							w.setVal((Integer) objectin.readObject());
							System.out.println(w.getInstructionString());
							server.widgetChanged(this, w);
						}
						System.out.println("1 - Widget received");
					} catch (ClassNotFoundException cnfe) {
						cnfe.printStackTrace();
					}
				break;
				
				case("gameMessage"):
					value2 = ((String) objectin.readObject()).trim();
					this.server.sendGameMessage(this, value2);
					break;
					
				case("waitingRoomMessage"):
					value2 = ((String) objectin.readObject()).trim();
					this.server.sendWaitingRoomMessage(this, value2);
					break;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			} //catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
 catch (ClassNotFoundException e) {
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
		this.milliseconds = milliseconds;
	}
	
	
	public void run(){
		try {
			System.out.println("Timer started");
			Thread.sleep(milliseconds);
			System.out.println("Timer ended");
			serverthread.timerDone();
		} catch (InterruptedException e) {
			System.out.println("Time interrupted");
			//No error here because the ServerThread may kill this when it's no longer needed;
		}
	}
}
