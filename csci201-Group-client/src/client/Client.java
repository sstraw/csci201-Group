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
import java.net.SocketException;
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
	
	JFrame wrFrame, loginFrame;
	JTextArea playerTA;
	JButton readyButton; //waiting room button
	
	private ReentrantLock lock = new ReentrantLock();
	private Socket s;
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
			s = new Socket("localhost", 55555);
			this.objectCannon = new ObjectOutputStream(s.getOutputStream());
			this.objectIn = new ObjectInputStream(s.getInputStream());
			thread = new Thread(this);
			thread.start();
			setPlayerName();
			displayWaitingRoomGUI();
			
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
		else{
			System.exit(0);
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
		wrFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
	private void setPlayerName(){
		lock.lock();
		try {
			this.objectCannon.writeObject(new String("setName"));
			this.objectCannon.writeObject(username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	private void setReady(boolean ready){
		lock.lock();
		try {
			System.out.println("3 - State Sending");
			this.objectCannon.writeObject(new String("setState"));
			if (ready){
				this.objectCannon.writeObject(new String("ready"));
			}
			else{
				this.objectCannon.writeObject(new String("notready"));
			}
			System.out.println("State sent");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	//Gives the server the list of widgets in use in the current dashboard
	public void giveWidgets(Vector<Widget> widgets){
		lock.lock();
		try {
			System.out.println("2 - Vector sending");
			this.objectCannon.writeObject(new String("giveWidgets"));
			this.objectCannon.writeObject(widgets);
			System.out.println("2 - Vector sent");
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	public void sendMessage(String msg){
		lock.lock();
		try {
			this.objectCannon.writeObject(new String("message"));
			this.objectCannon.writeObject(new String(msg));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	public void updateWidget(Widget w) {
		lock.lock();
		try {
			System.out.println("1 Sending widget update");
			System.out.println("  " + w.getInstructionString());
			objectCannon.writeObject(new String("widgetChanged"));
			objectCannon.writeObject(w);
			objectCannon.writeObject(w.getVal());
			System.out.println("1 Update sent");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}

	public void GameOverGUI() {	
		JFrame finalframe = new JFrame("Game Over");
	}
	
	public void run() {
		while (true){
			//Receive and process input
			try {
				String value1 = ((String) objectIn.readObject()).trim();;
				String value2;
				Object o;
				
				//Switch values
				switch(value1){
				
				case("connected user"):
					value2 = ((String) objectIn.readObject()).trim();;
					if (clientState == WAITINGROOM){
						playerTA.append("\n" + value2);
					}
					break;
				case("startLevel"):
					int level = (Integer) objectIn.readObject();
					int ind = (Integer) objectIn.readObject();
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
					o = objectIn.readObject();
					int time = (Integer) objectIn.readObject();
					if (o instanceof Widget){
						Widget w = (Widget) o;
						String instruction = w.getInstructionString();
						clientGUI.updateInstruction(instruction, time);
					}
					break;
					
				case("instruction completed"):
					System.out.println("Instruction completed");
					
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
					break;
				case("instruction failed"):
					System.out.println("Instruction failed");
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
					break;
				case("game over"):
					clientGUI.closeClientGUI();
					JOptionPane.showMessageDialog(null,
							"Game Over",
							"Whoops",
							JOptionPane.ERROR_MESSAGE);				
					break;
					
				case("message"):
					value2 = ((String) objectIn.readObject()).trim();
					clientGUI.receiveMessage(value2);
					break;
					/*value2 = buffer.readLine().trim();
					this.Client.sendMessage(this, value2);
					break; */
				}
			} catch (SocketException e){
				System.out.println("Socket closed. Quitting...");
				return;
			} catch (IOException e) {
				System.out.println("Socket closed. Quitting...");
				return;
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

