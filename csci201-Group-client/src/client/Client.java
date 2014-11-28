package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Client extends Thread {
	
	ArrayList<JPanel> levelOneDashboards; //will hold hardcoded set of Dashboards for each level
	ArrayList<JPanel> levelTwoDashboards;
	ArrayList<JPanel> levelThreeDashboards;
	ArrayList<JPanel> levelFourDashboards;
	ArrayList<JPanel> levelFiveDashboards;
	
	private int currentLevel;
	private boolean waitingRoom = true;
	
	// Chat variables
	private static Semaphore semaphore = new Semaphore(4);
	private Socket s;
	private PrintWriter pw;
	private BufferedReader br;
	private ObjectOutputStream objectcannon;
	private ObjectInputStream objectin;
	
	private JTextArea dashCommand = new JTextArea();
	private ClientGUI clientGUI = new ClientGUI( dashCommand );
	

	
	public Client(String hostname, int port) {
		
		
		//this event gets called whenever an action is done on the dashboard
		dashCommand.getDocument().addDocumentListener(new DocumentListener() {

		    public void removeUpdate(DocumentEvent e) {
		       // System.out.println("removeUpdate");
		    }

		    public void insertUpdate(DocumentEvent e) {
		    	command(dashCommand.getText());
		    	
		    	
		    }

		    public void changedUpdate(DocumentEvent e) {
		       // System.out.println("changedUpdate");
		    }
		});
		
		// Establish connection to server
		try {
			s = new Socket(hostname, port);
			this.pw = new PrintWriter(s.getOutputStream());
			this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			this.objectcannon = new ObjectOutputStream(s.getOutputStream());
			this.objectin = new ObjectInputStream(s.getInputStream());
			this.start();
		
//			while (true) {
//				semaphore.acquire();
//				if(clientGUI.sendMessage() == true ){
//					String line = clientGUI.getChatMessage();
//					System.out.println("YOUR LINE: " + line);
//					pw.println(line);
//					pw.flush();
//				}
//				semaphore.release();
//			}
		} catch (IOException ioe) {
			System.out.println("ioe in ChatClient: " + ioe.getMessage());
		}
	}
	
	// Still have to figure out how we're sending the dashboards 
		// to the clientGUIs.
		/*
		void chooseDashboard(int index) {  //
			if (currentLevel==1) { 
				currentDashboard = levelOneDashboards.get(index);
			} else if (currentLevel==2) {
				currentDashboard = levelTwoDashboards.get(index);
			} else if (currentLevel==3) {
				currentDashboard = levelThreeDashboards.get(index);
			} else if (currentLevel==4) {
				currentDashboard = levelFourDashboards.get(index);
			} else if (currentLevel==5) {
				currentDashboard = levelFiveDashboards.get(index);
			}
		}*/
		
	
	public void run() {
		try {
			semaphore.acquire();
			while(true) {
				String line;
				while((line = br.readLine()) != null){
					System.out.println("FROM OTHER CLIENT: " + line);
					clientGUI.receiveMessage(line);
				}
				semaphore.release();
			}
		} catch (IOException | InterruptedException ioe) {
			System.out.println("ioe in run: " + ioe.getMessage());
		}
	}
	
	public static void main(String [] args) {
		// We will need to get the IP
		 try {
	            // Set cross-platform Java L&F (also called "Metal")
	        UIManager.setLookAndFeel(
	            UIManager.getCrossPlatformLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
		new Client("10.123.43.191", 10000);
	}
	
	public void command(String c){
		
		//this is where you can check if the command matches the instruction that was given
		System.out.println( c );
	}
	
	
	//Server communications:
	
	//Suggested server run
	public void Otherrun() {
		try {
			while(true){
				String value1 = br.readLine().trim();
				String value2; Object o;
				switch(value1){
				case("instruction completed"):
					//Handle it?
					o = objectin.readObject();
					if (o instanceof Widget){
						Widget w = (Widget) o;
						//Assign instruction
					}
					else{
						System.out.println("WRONG OBJECT RECEIVED");
					}
					break;
					
				case("instruction failed"):
					//Handle it?
					o = objectin.readObject();
					if (o instanceof Widget){
						Widget w = (Widget) o;
						//Assign instruction
					}
					else{
						System.out.println("WRONG OBJECT RECEIVED");
					}
					break;
				
				case("message"):
					value2 = br.readLine().trim();
					//Handle the string? Show it in the box.
				
				case("startLevel"):
					int level = Integer.parseInt(br.readLine().trim());
					//Handle what to do with the level number,
					//Start a new level
				
				case("game over"):
					//Do whatever needs to be done
					break;
				}
			}
		} catch (IOException ioe) {
			System.out.println("ioe in run: " + ioe.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Changes the players name on the server (Should be handled only in lobby)
	
	private void setServerName(String s){
		this.pw.println("setName");
		this.pw.println(s);
	}
	
	private void setReady(boolean ready){
		this.pw.println("setState");
		if (ready){
			this.pw.println("ready");
		}
		if (ready){
			this.pw.println("notready");
		}
	}
	
	//Gives the server the list of widgets in use in the current dashboard
	private void giveWidgets(Vector<Widget> widgets){
		try {
			this.pw.println("giveWidgets");
			this.objectcannon.writeObject(widgets);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendMessage(String msg){
		this.pw.println("message");
		this.pw.println(msg);
	}
}

