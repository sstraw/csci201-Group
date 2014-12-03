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
import java.io.Serializable;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class WaitingRoomGUI extends JFrame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Client theClient;
	private String message = "";
	private boolean sendMessage = false;
	JPanel playerTA = new JPanel();
	Vector<JLabel> playerReadyLabels = new Vector<JLabel>();
	private JButton readyButton;
	private JTextArea waitingRoomChat;
	private JTextArea playerChat;
	private JScrollPane waitingRoomChatScrollPane;
	
	ImageIcon avatar1 = new ImageIcon("Images/avatar1.jpg");
	ImageIcon avatar2 = new ImageIcon("Images/avatar2.jpg");
	ImageIcon avatar3 = new ImageIcon("Images/avatar3.jpg");
	ImageIcon avatar4 = new ImageIcon("Images/avatar4.jpg");
	
	public WaitingRoomGUI(Client client){
		super("Mission Control");
		setSize(750, 750);
		setLocation(250, 25); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		theClient = client;
		
		JPanel wrPanel = new JPanel();
		wrPanel.setLayout(new BorderLayout());
		//playerTA.setText(client.username + " ... Preparing for launch!");
		//playerTA.setEditable(false);
		playerTA.setBackground(Color.BLACK);
		//playerTA.setForeground(Color.WHITE);
		playerTA.setLayout(new GridLayout(2,2));
		playerTA.setPreferredSize(new Dimension(550, 700));
		//playerTA.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		
		readyButton = new JButton("Ready for Take-Off!");
		readyButton.setBackground(Color.GREEN);
		wrPanel.add(playerTA, BorderLayout.CENTER);
		wrPanel.add(readyButton, BorderLayout.SOUTH);
		
		// Chat Panel:
		// Chat panel will contain 2 JTextAreas
		JPanel chatPanel = new JPanel();
		chatPanel.setPreferredSize(new Dimension(200, 750));
		
		// waitingRoomChat will hold all chat messages
		waitingRoomChat = new JTextArea();
		waitingRoomChat.setWrapStyleWord(true);
        waitingRoomChat.setLineWrap(true);  
        waitingRoomChat.setFocusable(false);

		waitingRoomChatScrollPane = new JScrollPane(waitingRoomChat);
		waitingRoomChatScrollPane.setBorder(BorderFactory.createTitledBorder("Space Chatter:"));
	    waitingRoomChatScrollPane.setViewportView(waitingRoomChat);
	    waitingRoomChatScrollPane.setPreferredSize(new Dimension(200, 500));
		
		// playerChat will hold player's current message
		playerChat = new JTextArea();
		playerChat.setWrapStyleWord(true);
		playerChat.setLineWrap(true);
		playerChat.setFocusable(true);
		JScrollPane playerChatScrollPane = new JScrollPane(playerChat);
		playerChatScrollPane.setBorder(BorderFactory.createTitledBorder("Your Message:"));
	    playerChatScrollPane.setViewportView(playerChat);
	    playerChatScrollPane.setPreferredSize(new Dimension(200, 250));
	    
		chatPanel.add(waitingRoomChatScrollPane, BorderLayout.NORTH);
		chatPanel.add(playerChatScrollPane, BorderLayout.SOUTH);
		wrPanel.add(chatPanel, BorderLayout.EAST);
		
		add(wrPanel, BorderLayout.CENTER);
		
		readyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (readyButton.getText().equals("Ready for Take-Off!")) {
					theClient.setReady(true);
					readyButton.setText("Abort Launch!");
					readyButton.setBackground(Color.RED);
					theClient.clientReady = true;
				} else {
					theClient.setReady(false);
					readyButton.setText("Ready for Take-Off!");
					readyButton.setBackground(Color.GREEN);
					theClient.clientReady = false;
				}
			}
		});
		
		//Configure KeyListener for keyboard
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
		      public boolean dispatchKeyEvent(KeyEvent e) {
		    	  if(e.getID() == KeyEvent.KEY_TYPED){
		    		  int keys = e.getKeyChar();
		    		  if(keys == KeyEvent.VK_ENTER){
						  message = playerChat.getText();
						  sendMessage = true;
						  playerChat.setText("");
						  playerChat.setCaretPosition(0);
						  theClient.sendWaitingRoomMessage(message);
				      }
		    		  else if(keys == KeyEvent.VK_BACK_SPACE){
		    			  message = playerChat.getText();
		    			  if(message.length() > 1)
		    				  message = message.substring(0, message.length() - 1);
		    			  else
		    				  message = "";
		    			  playerChat.setText(message);
		    		  }
		    		  else{	playerChat.append(e.getKeyChar() + "");	  }	  }
		    	  return false;
		      }
		});
		
		setVisible(true);
	}
	
	public void addUser(String user, String avatar){
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
		userPanel.setBackground(Color.BLACK);
		JLabel userLabel = new JLabel(user);
		userLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		userLabel.setForeground(Color.GREEN);
		playerReadyLabels.add(userLabel);
		
		System.out.println(user + " " + avatar);
	      if(avatar.equals("avatar1"))
	    	  userPanel.add(new JLabel(avatar1));
	      else if(avatar.equals("avatar2"))
	    	  userPanel.add(new JLabel(avatar2));
	      else if(avatar.equals("avatar3"))
	    	  userPanel.add(new JLabel(avatar3));
	      else if(avatar.equals("avatar4"))
	    	  userPanel.add(new JLabel(avatar4));
	      
		userPanel.add(userLabel);
		playerTA.add(userPanel);
		pack();
		repaint();
	}
	
	public void updateLabel(String username, String newLabel){
		for(JLabel label : playerReadyLabels){
			if(label.getText().contains(username)){
				label.setText(newLabel);
				if(newLabel.contains("Take-Off!")){
					label.setForeground(Color.RED);
				}
				else if(newLabel.contains("launch")){
					label.setForeground(Color.GREEN);
				}
			}
		}
	}
	
	public void receiveMessage(String newMessage){
		waitingRoomChat.append(newMessage + '\n');
		JScrollBar vertical = waitingRoomChatScrollPane.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );
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
}
