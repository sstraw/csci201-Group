package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Dashboard5_2 extends JPanel{

private JTextArea command;
	
	
	private static final long serialVersionUID = 1L;
	public Dashboard5_2( JTextArea d ){
		
		command = d;
		this.setLayout( new GridLayout(2 ,1) );
		
		
		//top row
		JPanel db1 = new JPanel();
		db1.setLayout( new BoxLayout( db1 , BoxLayout.LINE_AXIS) );
		db1.setBackground( Color.black);
		this.add( db1 );
		db1.add(Box.createRigidArea(new Dimension(12, 0)));
		JPanel sec1 = new JPanel();
		db1.add( sec1 );
		sec1.setLayout( new BoxLayout( sec1 , BoxLayout.PAGE_AXIS) );
		sec1.setBorder( BorderFactory.createLineBorder(Color.black) );
		
		sec1.add(Box.createRigidArea(new Dimension(525, 20)));
		
		JLabel gridText = new JLabel("LOGIC CONTROL BOARD");
		sec1.add(gridText );
		gridText.setAlignmentX( Component.CENTER_ALIGNMENT );
		gridText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		JPanel buttonGrid = new JPanel();
		buttonGrid.setLayout( new GridLayout(3 ,3) );
		buttonGrid.setMaximumSize( new Dimension(400,180 ));
		for(int i =0; i < 9; i++){
			final JToggleButton temp = new JToggleButton( String.valueOf(i+1));
			buttonGrid.add(temp);
			
			temp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					command.setText("SELECT LOGIC CONTROL BOARD " +  temp.getText() );	
				}
			});
		}
		sec1.add(Box.createRigidArea(new Dimension(0, 10)));
		sec1.add ( buttonGrid );
		sec1.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		db1.add(Box.createRigidArea(new Dimension(12, 0)));
		
		
		//bottom row

		JPanel db2 = new JPanel();
		db2.setLayout( new BoxLayout( db2 , BoxLayout.LINE_AXIS) );
		db2.setBackground( Color.black);
		this.add( db2 );
		
		db2.add(Box.createRigidArea(new Dimension(12, 0)));
		JPanel sec3 = new JPanel();
		
		sec3.setLayout( new BoxLayout( sec3 , BoxLayout.PAGE_AXIS) );
		sec3.setBorder( BorderFactory.createLineBorder(Color.black) );
		
		sec3.add(Box.createRigidArea(new Dimension(355, 30)));
		
		JLabel phaseText = new JLabel("EPSILON TRAP");
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
		slider.addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent ce) {
	        	JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting())
                {
                	//System.out.println( "PHASON COLLIDER SET TO " +  source.getValue() );
                	command.setText( "SET EPSILON TRAP TO " + source.getValue() );
                }
	        }
	    });
		
		//defribilator
		//hahahaha
		//Supercalifragilisticexpialidocious
		
		
		JPanel sec4 = new JPanel();
		db2.add( sec4 );
		
		db2.add(Box.createRigidArea(new Dimension(12, 0)));
		
		db2.add( sec3 );
		sec4.setLayout( new BoxLayout( sec4 , BoxLayout.PAGE_AXIS) );
		sec4.setBorder( BorderFactory.createLineBorder(Color.black) );
		
		sec4.add(Box.createRigidArea(new Dimension(155, 30)));
		JLabel whittlerText = new JLabel("EMERGENCY");
		whittlerText.setAlignmentX( Component.CENTER_ALIGNMENT );
		whittlerText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec4.add( whittlerText );
		JLabel whittlerText2 = new JLabel("WHITTLER");
		whittlerText2.setAlignmentX( Component.CENTER_ALIGNMENT );
		whittlerText2.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec4.add( whittlerText2 );
		sec4.add(Box.createRigidArea(new Dimension(0, 30)));
		final JButton baste = new JButton("BASTE");
		sec4.add(baste);
		baste.setAlignmentX( Component.CENTER_ALIGNMENT );
		baste.setMaximumSize( new Dimension(100, 50));
		
		baste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				command.setText( "BASTE THE EMERGENCY WHITTLER" );
			}
		});
		sec4.add(Box.createRigidArea(new Dimension(0, 20)));
		final JButton jiggle = new JButton("JIGGLE");
		sec4.add(jiggle);
		jiggle.setAlignmentX( Component.CENTER_ALIGNMENT );
		jiggle.setMaximumSize( new Dimension(100, 50));
		
		jiggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				command.setText( "JIGGLE THE EMERGENCY WHITTLER" );
			}
		});
		
		sec4.add(Box.createRigidArea(new Dimension(0, 24)));
		
	}

}



