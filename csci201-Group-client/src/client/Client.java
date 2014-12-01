package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



public class Client implements Runnable {
	final static public int WAITINGROOM = 1;
	final static public int INGAME = 2;
	final static public int GAMEOVER = 3;
	
	
	Vector<Dashboard> levelOneDashboards; //will hold hardcoded set of Dashboards for each level
	Vector<Dashboard> levelTwoDashboards;
	Vector<Dashboard> levelThreeDashboards;
	Vector<Dashboard> levelFourDashboards;
	Vector<Dashboard> levelFiveDashboards;
	
	String hostIP;
	String username;
	private int clientState = Client.WAITINGROOM;
	
	JFrame wrFrame;
	JTextArea playerTA;
	JButton readyButton; //waiting room button
	
	private ReentrantLock lock = new ReentrantLock();
	
	// Chat variables
	private static Semaphore semaphore = new Semaphore(4);
	private Socket s;
	private PrintWriter printWriter;
	private BufferedReader buffer;
	private ObjectOutputStream objectCannon;
	private ObjectInputStream objectIn;
	
	private JTextArea dashCommand = new JTextArea();
	private ClientGUI clientGUI; 
	
	private Thread thread;
	

	
	public Client() {
		
		
		addGUIActions();
		
		displayLoginGUI();
		// Establish connection to server
		try {
			System.out.println("trying to connect");
			s = new Socket("localhost", 55555);
			System.out.println("connected");
			this.printWriter = new PrintWriter(s.getOutputStream());
			this.buffer = new BufferedReader(new InputStreamReader(s.getInputStream()));
			this.objectCannon = new ObjectOutputStream(s.getOutputStream());
			this.objectIn = new ObjectInputStream(s.getInputStream());
			setPlayerName();
			displayWaitingRoomGUI();
			
			thread = new Thread(this);
			thread.start();
			
			
			//clientGUI = new ClientGUI( dashCommand );
			
			
			
		
//			while (true) {
//				semaphore.acquire();
//				if(clientGUI.sendMessage() == true ){
//					String line = clientGUI.getChatMessage();
//					System.out.println("YOUR LINE: " + line);
//					printWriter.println(line);
//					printWriter.flush();
//				}
//				semaphore.release();
//			}
		} catch (IOException ioe) {
			System.out.println("ioe in ChatClient: " + ioe.getMessage());
		}
	}
	
	private void addGUIActions() {
		
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
	}
	
	private void displayLoginGUI() {	
		JPanel loginPanel = new JPanel();
				
		loginPanel.setLayout(new BorderLayout());
		
		JPanel ipPanel = new JPanel();
		JLabel hostLab = new JLabel("Host IP: ");
		ipPanel.add(hostLab);
		JTextField ipTF = new JTextField(30);
		ipPanel.add(ipTF);
		loginPanel.add(ipPanel, BorderLayout.NORTH);
		
		JPanel unPanel = new JPanel();
		JLabel unLab = new JLabel("Username: ");
		unPanel.add(unLab);
		JTextField unTF = new JTextField(25);
		unPanel.add(unTF);
		loginPanel.add(unPanel, BorderLayout.CENTER);
		JButton okButton = new JButton("OK");
		loginPanel.add(unPanel, BorderLayout.SOUTH);
		
		ImageIcon icon = new ImageIcon("Images/rocketicon.jpg");
		
		int result = JOptionPane.showConfirmDialog(null, loginPanel, 
	               "Welcome to Space Team", JOptionPane.DEFAULT_OPTION,  JOptionPane.PLAIN_MESSAGE, icon);
		if (result == JOptionPane.OK_OPTION) {  	  
			hostIP = ipTF.getText();
			username = unTF.getText();
		} 
	}
	
	public void displayWaitingRoomGUI() {
		JPanel wrPanel = new JPanel();
		wrPanel.setLayout(new BorderLayout());
		playerTA = new JTextArea(username, 4, 20);
		playerTA.setEditable(false);
		readyButton = new JButton("Ready");
		wrPanel.add(playerTA, BorderLayout.CENTER);
		wrPanel.add(readyButton, BorderLayout.SOUTH);
		wrFrame = new JFrame("Waiting Room");
		wrFrame.add(wrPanel);
		wrFrame.setSize(300, 200);
		wrFrame.setLocationRelativeTo(null);
		wrFrame.setVisible(true);
		
		readyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (readyButton.getText().equals("Ready")) {
					setReady(true);
					readyButton.setText("Not Ready");
				} else {
					setReady(false);
					readyButton.setText("Ready");
				}
			}
		});
	}
	
	public void command(String c){		
		//this is where you can check if the command matches the instruction that was given
		System.out.println( c );
	}
	
	
	private void setPlayerName(){
		this.printWriter.println("setName");
		printWriter.flush();
		this.printWriter.println(username);
		printWriter.flush();
	}
	
	private void setReady(boolean ready){
		this.printWriter.println("setState");
		printWriter.flush();
		if (ready){
			this.printWriter.println("ready");
			printWriter.flush();
		}
		if (!ready){
			this.printWriter.println("notready");
			printWriter.flush();
		}
		System.out.println("state sent");
	}
	
	//Gives the server the list of widgets in use in the current dashboard
	public void giveWidgets(Vector<Widget> widgets){
		lock.lock();
		try {
			System.out.println("Client: widgets size: " + widgets.size());
			this.printWriter.println("giveWidgets");
			printWriter.flush();
			this.objectCannon.writeObject(widgets);
			objectCannon.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	private void sendMessage(String msg){
		this.printWriter.println("message");
		this.printWriter.println(msg);
	}
	
	public void updateWidget(Widget w) {
		try {
			objectCannon.writeObject(w);
			objectCannon.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
	
	public void run() {
		while (true){
			//Receive and process input
			try {
				System.out.println("Loop");
				String value1 = buffer.readLine().trim();
				String value2;
				Object o;
				
				//Switch values
				switch(value1){
				
				case("connected user"):
					value2 = buffer.readLine().trim();
					playerTA.append(value2);
					break;
				case("startLevel"):
					System.out.println("start level");
					int level = Integer.parseInt(buffer.readLine().trim());
					int ind = Integer.parseInt(buffer.readLine().trim());
					if (clientState == WAITINGROOM) {
						wrFrame.dispose();
						clientGUI = new ClientGUI(dashCommand, this);
						clientGUI.setDashboard(level, ind);
						clientState = INGAME;
					} else if (clientState == INGAME) {
						clientGUI.setDashboard(level, ind);
					}
					break;
					
				case("instruction"):
					System.out.println("client receiving instruction");
					o = objectIn.readObject();
					System.out.println("got obj");
					int time = Integer.parseInt(buffer.readLine().trim());
					System.out.println("got time");
					if (o instanceof Widget){
						System.out.println("casting to widget");
						Widget w = (Widget) o;
						String instruction = w.getInstructionString();
						System.out.println("updating instruction");
						clientGUI.updateInstruction(instruction, time);
					}
					break;
					
				case("instruction completed"):
					//do GUI shit
					
					/*
					o = objectIn.readObject();
					if (o instanceof Widget){
						Widget w = (Widget) o;
					//Assign instruction
					}
					else{
						System.out.println("WRONG OBJECT RECEIVED");
					}
					break;
					*/

				case("instruction failed"):
					//do GUI shit
					
					/*
					o = objectIn.readObject();
					if (o instanceof Widget){
						Widget w = (Widget) o;
						//Assign instruction
					}
					else{
						System.out.println("WRONG OBJECT RECEIVED");
					}
					break; */
					
				case("game over"):
					//Do shit
					break;
					
				case("message"):
					value2 = buffer.readLine().trim();
					/*value2 = buffer.readLine().trim();
					this.Client.sendMessage(this, value2);
					break; */
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
		new Client();
	}
}

