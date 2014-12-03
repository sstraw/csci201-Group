package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

	String hostIP;
	String username;
	boolean clientReady;
	int clientState = Client.WAITINGROOM;
	boolean endFlag = true;
	
	private ReentrantLock lock = new ReentrantLock();
	private Socket s;
	private ObjectOutputStream objectCannon;
	private ObjectInputStream objectIn;
	private JTextArea dashCommand = new JTextArea();
	private ClientGUI clientGUI; 	
	private WaitingRoomGUI wrGUI;
	private Thread thread;

	public Client() {
		
		addGUIActions();
		displayLoginGUI();
		// Establish connection to server
		try {
			s = new Socket("10.123.160.76", 55555);
			this.objectCannon = new ObjectOutputStream(s.getOutputStream());
			this.objectIn = new ObjectInputStream(s.getInputStream());
			thread = new Thread(this);
			thread.start();
			setPlayerName();
			
		} catch (IOException ioe) {
			//System.out.println("ioe in ChatClient: " + ioe.getMessage());
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
		loginPanel.add(unPanel, BorderLayout.SOUTH);
		
		ImageIcon icon = new ImageIcon("Images/rocketicon.jpg");
		
		int result = JOptionPane.showConfirmDialog(null, loginPanel, 
	               "Welcome to Space Team", JOptionPane.DEFAULT_OPTION,  JOptionPane.PLAIN_MESSAGE, icon);
		if (result == JOptionPane.OK_OPTION) {  	  
			hostIP = ipTF.getText();
			username = unTF.getText();
			displayWaitingRoomGUI();
		} 
		else{
			System.exit(0);
		}
	}
	
	private void displayWaitingRoomGUI() {
		wrGUI = new WaitingRoomGUI(this);
	}
	
	private void setPlayerName(){
		lock.lock();
		try {
			this.objectCannon.writeObject(new String("setName"));
			this.objectCannon.writeObject(username);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
		System.out.println("My username is: " + username);
	}
	
	public void setReady(boolean ready){
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
	public void sendInGameMessage(String msg){
		lock.lock();
		try {
			this.objectCannon.writeObject(new String("gameMessage"));
			this.objectCannon.writeObject(new String(msg));
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public void sendWaitingRoomMessage(String msg){
		lock.lock();
		try {
			this.objectCannon.writeObject(new String("waitingRoomMessage"));
			this.objectCannon.writeObject(new String(msg));
		} catch (IOException e) {
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
					value2 = ((String) objectIn.readObject()).trim();
					if (clientState == WAITINGROOM){
						wrGUI.playerTA.append("\n" + value2 + " ... Preparing for launch!");
					}
					break;
					
				case("setReady"):
					value2 = ((String) objectIn.readObject()).trim();
					String value3 = ((String) objectIn.readObject()).trim();
					updateWaitingRoom(value3, value2);
					break;
					
				case("startLevel"):
					int level = (Integer) objectIn.readObject();
					int ind = (Integer) objectIn.readObject();
					if (clientState == WAITINGROOM) {
						wrGUI.setFocusable(false);
						wrGUI.dispose();
						clientGUI = new ClientGUI(dashCommand, this);
						clientGUI.setDashboard(level, ind);
						clientGUI.setFocusable(true);
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
					//System.out.println("Instruction completed");
					
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
					//System.out.println("Instruction failed");
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
					if (endFlag) {
						JOptionPane.showMessageDialog(null,
								"Game Over",
								"Whoops",
								JOptionPane.ERROR_MESSAGE);	
						endFlag=false;
					}
					break;
					
				case("gameMessage"):
					value2 = ((String) objectIn.readObject()).trim();
					clientGUI.receiveMessage(value2);
					break;
				
				case("waitingRoomMessage"):
					value2 = ((String) objectIn.readObject()).trim();
					wrGUI.receiveMessage(value2);
					break;

				}
			} catch (SocketException e){
				//System.out.println("Socket closed. Quitting...");
				return;
			} catch (IOException e) {
				//System.out.println("Socket closed. Quitting...");
				return;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateWaitingRoom(String username, String readyStatus){
		String[] oldLines = wrGUI.playerTA.getText().split("\n");
		Vector<String> newLines = new Vector<String>();
		for(String line : oldLines){
			String wrStatus = "";
			if(line.contains(username)){
				if(readyStatus.equals("ready")){
					wrStatus = " ... Ready for Take-Off!";
				}
				else if (readyStatus.equals("notready")){
					wrStatus = " ... Preparing for launch!";
				}
				newLines.add(username + wrStatus);
			}
			else if(line.contains(this.username)){
				if(clientReady){
					wrStatus = " ... Ready for Take-Off!";
				}
				else if (!clientReady){
					wrStatus = " ... Preparing for launch!";
				}
				newLines.add(this.username + wrStatus);
			}
			else
				newLines.add(line);
		}
		wrGUI.playerTA.setText("");
		for(String line : newLines){
			wrGUI.playerTA.append(line + "\n");
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

