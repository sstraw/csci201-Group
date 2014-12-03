package client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.io.*;
import java.util.Vector;

public class ClientGUI extends JFrame implements Serializable {
	
	Client client;
	
	private static final long serialVersionUID = 1L;
	
	protected int currentLevel;
	JTextField instruction;

	//private JPanel currentDashboard;
	private DashboardFactory dbFactory = new DashboardFactory();
	private JPanel currentDashboard;
	private JPanel dbContainer;
	private JProgressBar progressBar;
	private JLabel shipAnimation;
	private ImageIcon shipIcon;
	
	private JTextArea groupChat;
	private JTextArea playerChat;
	private JScrollPane groupChatScrollPane;
	private String message = "";
	private boolean sendMessage;
	private Timer timer;
	private ActionListener listener;
	private int counter;
	boolean resetTimer = false;
	boolean firstTime = true;
	
	
	public ClientGUI(JTextArea d, Client cl) {
		client = cl;	
		
		createGUI();
		sendMessage = false;
		//Configure KeyListener for keyboard
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
		  .addKeyEventDispatcher(new KeyEventDispatcher() {
		      @Override
		      public boolean dispatchKeyEvent(KeyEvent e) {
		    	  if(e.getID() == KeyEvent.KEY_TYPED){
		    		  int keys = e.getKeyChar();
		    		  if(keys == KeyEvent.VK_ENTER){
						  message = playerChat.getText();
						  sendMessage = true;
						  playerChat.setText("");
						  playerChat.setCaretPosition(0);
						  client.sendInGameMessage(message);
				      }
		    		  else if(keys == KeyEvent.VK_BACK_SPACE){
		    			  message = playerChat.getText();
		    			  message = message.substring(0, message.length() - 1);
		    			  playerChat.setText(message);
		    		  }
		    		  else{	playerChat.append(e.getKeyChar() + "");	  }	  }
		    	  return true;
		      }
		});
		
		listener = new ActionListener() {
		       public void actionPerformed(ActionEvent ae) {
		           counter--;
		           progressBar.setValue(counter);
		           if (counter<1) {
		               timer.stop();
		           } 
		       }
		   };
	}
	
	public void receiveMessage(String newMessage){
		groupChat.append(newMessage + '\n');
		JScrollBar vertical = groupChatScrollPane.getVerticalScrollBar();
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
	
	/*public void setDashboard(JPanel dashboard){
		this.dashboard = dashboard;
		add(this.dashboard, BorderLayout.CENTER);
	}*/

	public void createGUI(){
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
		shipIcon = new ImageIcon("Images/ship" + 9 + ".jpg");
		shipAnimation.setIcon(shipIcon);
		shipAnimation.setIcon(shipIcon);
		shipAnimation.setBorder( BorderFactory.createLineBorder(Color.black) );
		shipPanel.add(shipAnimation);
		gamePanel.add(shipPanel);
		
		//text field showing the recieved instruction
		instruction = new JTextField("INSTRUCTIONS GO HERE");
		gamePanel.add(instruction);
		instruction.setMaximumSize( new Dimension (550, 50));
		instruction.setEditable(false);	
		instruction.setHorizontalAlignment(JTextField.CENTER);
		
		//progress bar
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(100);
		progressBar.setForeground( Color.GREEN);			
		gamePanel.add(progressBar);
		
		
        listener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                counter--;
                progressBar.setValue(counter);
                if (counter<1) {
                    timer.stop();
                } 
            }
        };
		
		dbContainer = new JPanel();
		dbContainer.setLayout(new BorderLayout());
		currentDashboard = new Dashboard1_1(client).getPanel();
		dbContainer.add(currentDashboard, BorderLayout.CENTER);
		gamePanel.add(dbContainer);		
		mainLayout.add(gamePanel, BorderLayout.CENTER);
		
		// Chat Panel:
		// Chat panel will contain 2 JTextAreas
		JPanel chatPanel = new JPanel();
		chatPanel.setPreferredSize(new Dimension(200, 700));
		
		// groupChat will hold all chat messages
		groupChat = new JTextArea();
		groupChat.setWrapStyleWord(true);
        groupChat.setLineWrap(true);      

		groupChatScrollPane = new JScrollPane(groupChat);
		groupChatScrollPane.setBorder(BorderFactory.createTitledBorder("Space Chatter:"));
	    groupChatScrollPane.setViewportView(groupChat);
	    groupChatScrollPane.setPreferredSize(new Dimension(200, 500));
		
		// playerChat will hold player's current message
		playerChat = new JTextArea();
		playerChat.setWrapStyleWord(true);
		playerChat.setLineWrap(true);
		playerChat.setFocusable(true);
		JScrollPane playerChatScrollPane = new JScrollPane(playerChat);
		playerChatScrollPane.setBorder(BorderFactory.createTitledBorder("Your Message:"));
	    playerChatScrollPane.setViewportView(playerChat);
	    playerChatScrollPane.setPreferredSize(new Dimension(200, 200));
	    
		chatPanel.add(groupChatScrollPane, BorderLayout.NORTH);
		chatPanel.add(playerChatScrollPane, BorderLayout.SOUTH);
		mainLayout.add(chatPanel, BorderLayout.EAST);
		
		add(mainLayout);
		setVisible(true);
	}	
	
	public void setDashboard(int lvl, int ind) {
		resetTimer=true;
		Dashboard temp = dbFactory.getDashboard(client, lvl, ind);	
		currentDashboard = temp.getPanel();
		dbContainer.removeAll();
		dbContainer.add(currentDashboard);
		Vector<Widget> wVect = temp.getWidgets();
		client.giveWidgets(wVect);
		this.validate();
		this.repaint();
	}
	
	public void updateInstruction(String inst, int timeLimit) {
		instruction.setText(inst);
		//System.out.println("Inst time limit: " + timeLimit/1000);
		counter = 100;
		progressBar.setValue(100);
		if (resetTimer) {
			if (!firstTime) {
				timer.removeActionListener(listener);
				timer.stop();
			} else {
				firstTime = false;
			}
			int inc = (timeLimit/100);
			System.out.println("Setting timer with inc = " + inc);
			timer = new Timer(inc, listener);		
		    timer.start();
		    resetTimer = false;
		}
		repaint();
	}
	
	public void closeClientGUI() {
		this.setVisible(false);
	}
	
}
