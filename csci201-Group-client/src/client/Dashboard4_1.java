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

public class Dashboard4_1  extends JPanel{
	
	private JTextArea command;
	
	
	private static final long serialVersionUID = 1L;
	public Dashboard4_1( JTextArea d ){
		
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
		

		sec1.add(Box.createRigidArea(new Dimension(255, 20)));
		
		JLabel ironText = new JLabel("TRIPLE GRID PLEXER");
		sec1.add( ironText );
		ironText.setAlignmentX( Component.CENTER_ALIGNMENT );
		ironText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		JPanel buttonGrid = new JPanel();
		buttonGrid.setLayout( new GridLayout(3 ,3) );
		buttonGrid.setMaximumSize( new Dimension(180,180 ));
		for(int i =0; i < 9; i++){
			final JButton temp = new JButton( String.valueOf(i+1));
			buttonGrid.add(temp);
			temp.setBackground( Color.green);
			temp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					if(temp.getBackground().equals(Color.green) ){
						temp.setBackground( Color.red);
						command.setText("SET TRIPLE GRID PLEXER " +  temp.getText() + " TO RED");
					}
					else{
						temp.setBackground( Color.green);	
						command.setText("SET TRIPLE GRID PLEXER " +  temp.getText() + " TO GREEN");
					}
				}
			});
		}
		sec1.add(Box.createRigidArea(new Dimension(0, 10)));
		sec1.add ( buttonGrid );
		sec1.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		db1.add(Box.createRigidArea(new Dimension(12, 0)));
		
		JPanel sec2 = new JPanel();
		sec2.setLayout( new BoxLayout( sec2 , BoxLayout.PAGE_AXIS) );
		sec2.setBorder( BorderFactory.createLineBorder(Color.black) );
		sec2.add(Box.createRigidArea(new Dimension(255, 30)));
		JLabel quantText = new JLabel("QUANTUM OMEGIFIER");
		quantText.setAlignmentX( Component.CENTER_ALIGNMENT );
		quantText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec2.add( quantText );
		sec2.add(Box.createRigidArea(new Dimension(0, 40)));
		JButton cable = new JButton("DISPERSE");
		sec2.add(cable);
		cable.setAlignmentX( Component.CENTER_ALIGNMENT );
		cable.setMaximumSize( new Dimension(120, 35));
		cable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
			}
		});
		sec2.add(Box.createRigidArea(new Dimension(0, 25)));
		JButton synth = new JButton("SYNTHESIZE");
		sec2.add(synth);
		synth.setAlignmentX( Component.CENTER_ALIGNMENT );
		synth.setMaximumSize( new Dimension(120, 35));
		synth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
			}
		});
		
		sec2.add(Box.createRigidArea(new Dimension(0, 55)));
		
		db1.add( sec2 );
		db1.add(Box.createRigidArea(new Dimension(10, 0)));
		
		//bottom row

		JPanel db2 = new JPanel();
		db2.setLayout( new BoxLayout( db2 , BoxLayout.LINE_AXIS) );
		db2.setBackground( Color.black);
		this.add( db2 );
		
		db2.add(Box.createRigidArea(new Dimension(12, 0)));
		JPanel sec3 = new JPanel();
		db2.add( sec3 );
		sec3.setLayout( new BoxLayout( sec3 , BoxLayout.PAGE_AXIS) );
		sec3.setBorder( BorderFactory.createLineBorder(Color.black) );
		
		sec3.add(Box.createRigidArea(new Dimension(355, 30)));
		
		JLabel phaseText = new JLabel("ELASTIC ILLUSIONER");
		phaseText.setAlignmentX( Component.CENTER_ALIGNMENT );
		phaseText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec3.add( phaseText );
		sec3.add(Box.createRigidArea(new Dimension(0, 70)));
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		slider.setMajorTickSpacing(2);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		sec3.add ( slider );
		slider.setMaximumSize( new Dimension(250, 35));
		sec3.add(Box.createRigidArea(new Dimension(0, 90)));
		
		//defribilator
		//hahahaha
		//Supercalifragilisticexpialidocious
		
		db2.add(Box.createRigidArea(new Dimension(12, 0)));
		JPanel sec4 = new JPanel();
		db2.add( sec4 );
		sec4.setLayout( new BoxLayout( sec4 , BoxLayout.PAGE_AXIS) );
		sec4.setBorder( BorderFactory.createLineBorder(Color.black) );
		
		sec4.add(Box.createRigidArea(new Dimension(155, 30)));
		JLabel beamText = new JLabel("MILK");
		beamText.setAlignmentX( Component.CENTER_ALIGNMENT );
		beamText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec4.add( beamText );
		JLabel beamText2 = new JLabel("MOLECULIZER");
		beamText2.setAlignmentX( Component.CENTER_ALIGNMENT );
		beamText2.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec4.add( beamText2 );
		sec4.add(Box.createRigidArea(new Dimension(0, 30)));
		final JToggleButton beamcable = new JToggleButton("DIRTY");
		sec4.add(beamcable);
		beamcable.setAlignmentX( Component.CENTER_ALIGNMENT );
		beamcable.setMaximumSize( new Dimension(100, 110));
		
		beamcable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if( beamcable.getText().equals("DIRTY")){
					beamcable.setText("CLEAN");
				}
				else{
					beamcable.setText("DIRTY");
				}
			}
		});
		sec4.add(Box.createRigidArea(new Dimension(0, 30)));
		
	}

}


