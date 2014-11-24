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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class Client extends Thread {
	ArrayList<JPanel> levelOneDashboards; //will hold hardcoded set of Dashboards for each level
	ArrayList<JPanel> levelTwoDashboards;
	ArrayList<JPanel> levelThreeDashboards;
	ArrayList<JPanel> levelFourDashboards;
	ArrayList<JPanel> levelFiveDashboards;
	
	private int currentLevel;
	private boolean waitingRoom = true;
	
	private JPanel currentDashboard;
	private JProgressBar progressBar;
	private JLabel shipAnimation;
	private ImageIcon shipIcon;

	
	public Client() {
		//establish connection with server
		initializeGUI();
		//initialize GUI components

		//display GUI
	}
	
	private void initializeGUI() {
		JFrame gameFrame = new JFrame();
		gameFrame.setSize(750, 700);
		gameFrame.setLocation(250, 25);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainLayout = new JPanel();
		mainLayout.setLayout(new BorderLayout());
		
		//panel that contains everything other than text 
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BoxLayout( gamePanel , BoxLayout.PAGE_AXIS));
		//gamePanel.add(Box.createRigidArea(new Dimension(0, 25)));
		
		shipAnimation = new JLabel();
		JPanel shipPanel = new JPanel();
		shipPanel.setLayout(new GridLayout (1,1));
		shipPanel.setMaximumSize( new Dimension (550, 100));
		shipAnimation.setMinimumSize( new Dimension(550, 100));
		shipAnimation.setAlignmentX(Component.LEFT_ALIGNMENT);
		shipIcon = new ImageIcon("ShipImages/ship" + 9 + ".jpg");
		shipAnimation.setIcon(shipIcon);
		shipAnimation.setIcon(shipIcon);
		shipAnimation.setBorder( BorderFactory.createLineBorder(Color.black) );
		shipPanel.add(shipAnimation);
		gamePanel.add(shipPanel);
		
		//text field showing the recieved instruction
		JTextField instruction = new JTextField("INSTRUCTIONS GO HERE");
		gamePanel.add( instruction );
		instruction.setMaximumSize( new Dimension (550, 50));
		instruction.setEditable(false);	
		instruction.setHorizontalAlignment(JTextField.CENTER);
		
		//progress bar
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(100);
		progressBar.setForeground( Color.GREEN);			
		gamePanel.add( progressBar );
		
		currentDashboard = new Dashboard1_1();
		currentDashboard.setLayout(new GridLayout(2 ,1));
		gamePanel.add(currentDashboard);		
		mainLayout.add(gamePanel, BorderLayout.CENTER);
		
		//Chat Panel
		JPanel chatPanel = new JPanel();
		chatPanel.setLayout(new GridLayout(1 ,1));
		chatPanel.setPreferredSize( new Dimension(200, 700));
		mainLayout.add(chatPanel, BorderLayout.EAST);
		chatPanel.setBackground(Color.WHITE);
		chatPanel.setBorder(BorderFactory.createLineBorder(Color.black));		
		JTextField keyInput = new JTextField("CHAT GOES HERE");		
		keyInput.setMaximumSize(new Dimension(50, 100));
		chatPanel.add(keyInput);
		//Thread count = new Thread (new missed());
		//count.start();
		
		//goes on the east of main
		gameFrame.add(mainLayout);
		gameFrame.setVisible(true);
		
	}
	
	void setLevel(int level) {
		
	}
	
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
		
	}
	
	public static void main(String [] args) {
		Client cl = new Client();
		
	}
	
}

