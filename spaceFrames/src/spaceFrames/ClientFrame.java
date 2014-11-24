package spaceFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ClientFrame extends JFrame{
	
	private JProgressBar progressBar;
	private JLabel shipAnimation;
	private ImageIcon shipIcon;
	private int time;
	private int missesLeft;
	 
	class missed extends Thread {
		
		public missed(){
			time = 100;
		}
		
	    public void run() {
	    	while ( true){
	    		//System.out.println("Time's up!");
	    		
	    		progressBar.setValue( time);
	    		
	    		if(time <= 100 && time >= 70){
	    			progressBar.setForeground( Color.green);
	    		}
	    		else if(time < 70 && time > 20 ){
	    			progressBar.setForeground( Color.orange);
	    		}
	    		else if(time <= 20 ){
	    			progressBar.setForeground( Color.red);
	    		}
	    		
	    		try {
					sleep( 10 );
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		if ( missesLeft == 0){
	    			missesLeft = 11;
	    		}
	    		if ( time == 0){
	    			missesLeft--;
	    			shipIcon = new ImageIcon("./ship" + String.valueOf( missesLeft ) + ".jpg");
		    		shipAnimation.setIcon(shipIcon);
		    		repaint();
	    			time = 100;
	    		}
	    		else{
	    			time--;
	    		}
	    		
	    	}
	    }
	  }
	
	public ClientFrame(){
		setSize(750, 700);
		setLocation(250, 25);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainLayout = new JPanel();
		mainLayout.setLayout( new BorderLayout() );
		
		//  panel that contains everything other than text 
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout( new BoxLayout( gamePanel , BoxLayout.PAGE_AXIS) );
		//gamePanel.add(Box.createRigidArea(new Dimension(0, 25)));
		
		
		//animation of the ship
		shipAnimation = new JLabel();
		JPanel ship = new JPanel();
		ship.setLayout( new GridLayout (1,1 ));
		ship.setMaximumSize( new Dimension (550, 100));
		shipAnimation.setMinimumSize( new Dimension(550, 100));
		shipAnimation.setAlignmentX( Component.LEFT_ALIGNMENT );
		shipIcon = new ImageIcon("./ship10.jpg");
		shipAnimation.setIcon(shipIcon);
		shipAnimation.setBorder( BorderFactory.createLineBorder(Color.black) );
		ship.add( shipAnimation );
		gamePanel.add( ship );
		
		
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
		
		
		//dashboard that contains all of the widgets
		JPanel Dashboard =  new JPanel();
		Dashboard.setLayout( new GridLayout(2 ,1) );
		gamePanel.add( Dashboard );
		
		//top row
		JPanel db1 = new JPanel();
		db1.setLayout( new BoxLayout( db1 , BoxLayout.LINE_AXIS) );
		db1.setBackground( Color.black);
		Dashboard.add( db1 );
		db1.add(Box.createRigidArea(new Dimension(12, 0)));
		JPanel sec1 = new JPanel();
		db1.add( sec1 );
		sec1.setLayout( new BoxLayout( sec1 , BoxLayout.PAGE_AXIS) );
		sec1.setBorder( BorderFactory.createLineBorder(Color.black) );
		

		sec1.add(Box.createRigidArea(new Dimension(255, 30)));
		
		JLabel ironText = new JLabel("INDUCTION IRON");
		sec1.add( ironText );
		ironText.setAlignmentX( Component.CENTER_ALIGNMENT );
		ironText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec1.add(Box.createRigidArea(new Dimension(0, 40)));
		JButton hot = new JButton ( "HOT" );
		sec1.add( hot );
		hot.setMaximumSize( new Dimension(120, 35));
		hot.setAlignmentX( Component.CENTER_ALIGNMENT );
		sec1.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton cold = new JButton ( "COLD" );
		sec1.add( cold );
		cold.setAlignmentX( Component.CENTER_ALIGNMENT );
		cold.setMaximumSize( new Dimension(120, 35));
		sec1.add(Box.createRigidArea(new Dimension(0, 70)));
		
		db1.add(Box.createRigidArea(new Dimension(12, 0)));
		
		JPanel sec2 = new JPanel();
		sec2.setLayout( new BoxLayout( sec2 , BoxLayout.PAGE_AXIS) );
		sec2.setBorder( BorderFactory.createLineBorder(Color.black) );
		sec2.add(Box.createRigidArea(new Dimension(255, 30)));
		JLabel cableText = new JLabel("GROOVED CABLE");
		cableText.setAlignmentX( Component.CENTER_ALIGNMENT );
		cableText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec2.add( cableText );
		sec2.add(Box.createRigidArea(new Dimension(0, 60)));
		JToggleButton cable = new JToggleButton("LOCK");
		sec2.add(cable);
		cable.setAlignmentX( Component.CENTER_ALIGNMENT );
		cable.setMaximumSize( new Dimension(120, 35));
		cable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if( cable.getText().equals("LOCK")){
					cable.setText("UNLOCK");
				}
				else{
					
					cable.setText("LOCK");
				}
				
			}
		});
		
		sec2.add(Box.createRigidArea(new Dimension(0, 95)));
		
		db1.add( sec2 );
		db1.add(Box.createRigidArea(new Dimension(10, 0)));
		
		//bottom row

		JPanel db2 = new JPanel();
		db2.setLayout( new BoxLayout( db2 , BoxLayout.LINE_AXIS) );
		db2.setBackground( Color.black);
		Dashboard.add( db2 );
		
		db2.add(Box.createRigidArea(new Dimension(12, 0)));
		JPanel sec3 = new JPanel();
		db2.add( sec3 );
		sec3.setLayout( new BoxLayout( sec3 , BoxLayout.PAGE_AXIS) );
		sec3.setBorder( BorderFactory.createLineBorder(Color.black) );
		
		sec3.add(Box.createRigidArea(new Dimension(355, 30)));
		
		JLabel phaseText = new JLabel("PHASON COLLIDER");
		phaseText.setAlignmentX( Component.CENTER_ALIGNMENT );
		phaseText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec3.add( phaseText );
		sec3.add(Box.createRigidArea(new Dimension(0, 70)));
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 4, 0);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		sec3.add ( slider );
		slider.setMaximumSize( new Dimension(250, 35));
		sec3.add(Box.createRigidArea(new Dimension(0, 90)));
		
		db2.add(Box.createRigidArea(new Dimension(12, 0)));
		JPanel sec4 = new JPanel();
		db2.add( sec4 );
		sec4.setLayout( new BoxLayout( sec4 , BoxLayout.PAGE_AXIS) );
		sec4.setBorder( BorderFactory.createLineBorder(Color.black) );
		
		sec4.add(Box.createRigidArea(new Dimension(155, 30)));
		JLabel beamText = new JLabel("SPECTROBEAM");
		beamText.setAlignmentX( Component.CENTER_ALIGNMENT );
		beamText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec4.add( beamText );
		
		mainLayout.add( gamePanel, BorderLayout.CENTER );
		
		//chatlayout
		JPanel chatPanel = new JPanel();
		chatPanel.setLayout( new GridLayout(1 ,1) );
		chatPanel.setPreferredSize( new Dimension(200, 700));
		mainLayout.add( chatPanel, BorderLayout.EAST );
		chatPanel.setBackground( Color.WHITE);
		chatPanel.setBorder( BorderFactory.createLineBorder(Color.black) );
		
		JTextField keyInput = new JTextField("          CHAT GOES HERE");
		
		keyInput.setMaximumSize( new Dimension(50, 100));

		chatPanel.add( keyInput );
		Thread count = new Thread ( new missed() );
		count.start();
		
		//goes on the east of main
		add( mainLayout );
		setVisible(true);
		
       
		missesLeft = 10;	
	}
	
	public static void main(String [] args) {
		
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
		new ClientFrame();
	}
	
}



