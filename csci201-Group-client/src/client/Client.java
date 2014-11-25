package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class Client extends Thread {
	
	Vector<JPanel> levelOneDashboards; //will hold hardcoded set of Dashboards for each level
	Vector<JPanel> levelTwoDashboards;
	Vector<JPanel> levelThreeDashboards;
	Vector<JPanel> levelFourDashboards;
	Vector<JPanel> levelFiveDashboards;
	
	String hostIP;
	String username;
	
	// Chat variables
	private static Semaphore semaphore = new Semaphore(4);
	private Socket s;
	private PrintWriter pw;
	private BufferedReader br;

	private JTextArea dashCommand = new JTextArea();
	private ClientGUI clientGUI; 
	

	
	public Client() {
		
		
		//this event gets called whenever an action is done on the dashboard
		dashCommand.getDocument().addDocumentListener(new DocumentListener() {

		    public void removeUpdate(DocumentEvent e) {
		       // System.out.println("removeUpdate");
		    }

		    public void insertUpdate(DocumentEvent e) {
		    	System.out.println( dashCommand.getText() );
		    }

		    public void changedUpdate(DocumentEvent e) {
		       // System.out.println("changedUpdate");
		    }
		});
		
		displayLoginGUI();
		clientGUI = new ClientGUI( dashCommand );
		
		// Establish connection to server
		try {
			s = new Socket(hostIP, 10000);
			this.pw = new PrintWriter(s.getOutputStream());
			this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			this.start();
		
			while (true) {
				semaphore.acquire();
				if(clientGUI.sendMessage() == true ){
					String line = clientGUI.getChatMessage();
					System.out.println("YOUR LINE: " + line);
					pw.println(line);
					pw.flush();
				}
				semaphore.release();
			}
		} catch (IOException | InterruptedException ioe) {
			System.out.println("ioe in ChatClient: " + ioe.getMessage());
		}
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
		unPanel.add(unLab, BorderLayout.SOUTH);
		JTextField unTF = new JTextField(30);
		unPanel.add(unTF, BorderLayout.SOUTH);
		loginPanel.add(unPanel, BorderLayout.SOUTH);
		
		ImageIcon icon = new ImageIcon("Images/rocketicon.jpg");
		
		int result = JOptionPane.showConfirmDialog(null, loginPanel, 
	               "Welcome to Space Team", JOptionPane.DEFAULT_OPTION,  JOptionPane.PLAIN_MESSAGE, icon);
      if (result == JOptionPane.OK_OPTION) {  	  
         hostIP = ipTF.getText();
         username = unTF.getText();
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
		new Client();
	}
	
}

