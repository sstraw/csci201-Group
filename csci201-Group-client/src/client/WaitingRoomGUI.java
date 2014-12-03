package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WaitingRoomGUI extends JFrame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Client theClient;
	private String message = "";
	private boolean sendMessage = false;
	JTextArea playerTA = new JTextArea(4, 20);
	private JButton readyButton;
	private JTextArea waitingRoomChat;
	private JTextArea playerChat;
	private JScrollPane waitingRoomChatScrollPane;
	
	public WaitingRoomGUI(Client client){
		super("Mission Control");
		setSize(750, 700);
		setLocation(250, 25); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		theClient = client;
		
		JPanel wrPanel = new JPanel();
		wrPanel.setLayout(new BorderLayout());
		playerTA.setText(client.username + " ... Preparing for launch!");
		playerTA.setEditable(false);
		playerTA.setBackground(Color.BLACK);
		playerTA.setForeground(Color.WHITE);
		readyButton = new JButton("Ready for Take-Off!");
		readyButton.setBackground(Color.GREEN);
		wrPanel.add(playerTA, BorderLayout.CENTER);
		wrPanel.add(readyButton, BorderLayout.SOUTH);
		
		// Chat Panel:
		// Chat panel will contain 2 JTextAreas
		JPanel chatPanel = new JPanel();
		chatPanel.setPreferredSize(new Dimension(200, 700));
		
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
	    playerChatScrollPane.setPreferredSize(new Dimension(200, 200));
	    
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
