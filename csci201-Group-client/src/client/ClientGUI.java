package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import java.io.*;
import java.util.ArrayList;

public class ClientGUI extends JFrame implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<JPanel> levelOneDashboards; //will hold hardcoded set of Dashboards for each level
	ArrayList<JPanel> levelTwoDashboards;
	ArrayList<JPanel> levelThreeDashboards;
	ArrayList<JPanel> levelFourDashboards;
	ArrayList<JPanel> levelFiveDashboards;
	
	protected int currentLevel;
	
	
	private JPanel currentDashboard;
	private JProgressBar progressBar;
	private JLabel shipAnimation;
	private ImageIcon shipIcon;
	
	private JTextArea groupChat;
	private JTextArea playerChat;
	private String message = "";
	private boolean sendMessage;
	private JPanel dashboard; 
	private JTextArea dashCommand;
	
	public ClientGUI( JTextArea d){
		
		dashCommand = d;
		
		System.out.println("Creating chat window.");
		createAndShowGUI();
		sendMessage = false;
		//Configure KeyListener for keyboard
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
		  .addKeyEventDispatcher(new KeyEventDispatcher() {
		      @Override
		      public boolean dispatchKeyEvent(KeyEvent e) {
		    	  if(e.getID() == KeyEvent.KEY_PRESSED){
		    		  int keys = e.getKeyCode();
		    		  if(keys == KeyEvent.VK_ENTER){
						  message = "[PLAYER_NAME]: " + playerChat.getText();
						  sendMessage = true;
						  playerChat.setText("");
						  playerChat.setCaretPosition(0);
				      }
		    		  else{	playerChat.append(e.getKeyChar() + "");	  }	  }
		    	  return true;
		      }
		});
	}
	
	public void receiveMessage(String newMessage){
		groupChat.append(newMessage + '\n');
	}
	
	public String getChatMessage(){
		String chatMessage = message;
		message = "";
		sendMessage = false;
		return chatMessage;
	}
	
	public boolean sendMessage(){
		return sendMessage;
	}
	
	public void setDashboard(JPanel dashboard){
		this.dashboard = dashboard;
		add(this.dashboard, BorderLayout.CENTER);
	}
	public void createAndShowGUI(){
		setSize(750, 700);
		setLocation(250, 25); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
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
		
		currentDashboard = new Dashboard5_2( dashCommand );
		currentDashboard.setLayout(new GridLayout(2 ,1));
		gamePanel.add(currentDashboard);		
		mainLayout.add(gamePanel, BorderLayout.CENTER);
		
		// Chat Panel:
		// Chat panel will contain 2 JTextAreas
		JPanel chatPanel = new JPanel();
		chatPanel.setPreferredSize(new Dimension(200, 700));
		
		// groupChat will hold all chat messages
		groupChat = new JTextArea();
		groupChat.setPreferredSize(new Dimension(200, 500));
		groupChat.setFocusable(false);
		
		// playerChat will hold player's current message
		playerChat = new JTextArea();
		playerChat.setPreferredSize(new Dimension(200, 200));
		playerChat.setWrapStyleWord(true);
		playerChat.setFocusable(true);
		
		chatPanel.add(groupChat, BorderLayout.NORTH);
		chatPanel.add(playerChat, BorderLayout.SOUTH);
		mainLayout.add(chatPanel, BorderLayout.EAST);
		
		add(mainLayout);
		setVisible(true);
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
}