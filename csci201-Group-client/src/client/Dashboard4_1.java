package client;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Dashboard4_1 implements Dashboard{
	private JPanel panel;
	private Vector<Widget> widgets;
	private Client client;
	public Dashboard4_1(Client c){
		client = c;
		panel = new JPanel();
		
		panel.setLayout( new GridLayout(2 ,1) );
		
		
		//top row
		JPanel db1 = new JPanel();
		db1.setLayout( new BoxLayout( db1 , BoxLayout.LINE_AXIS) );
		db1.setBackground( Color.black);
		panel.add( db1 );
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
		hot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				widgets.get(0).setVal(0);
				client.updateWidget(widgets.get(0));
			}
		});
		sec1.add( hot );
		hot.setMaximumSize( new Dimension(120, 35));
		hot.setAlignmentX( Component.CENTER_ALIGNMENT );
		sec1.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton cold = new JButton ( "COLD" );
		cold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				widgets.get(0).setVal(1);
				client.updateWidget(widgets.get(0));
			}
		});
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
		final JToggleButton cable = new JToggleButton("LOCKED");
		sec2.add(cable);
		cable.setAlignmentX( Component.CENTER_ALIGNMENT );
		cable.setMaximumSize( new Dimension(120, 35));
		cable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if( cable.getText().equals("LOCKED")){
					cable.setText("UNLOCKED");
					widgets.get(1).setVal(1);
				}
				else{
					cable.setText("LOCKED");
					widgets.get(1).setVal(0);
				}
				client.updateWidget(widgets.get(1));
			}
		});
		
		sec2.add(Box.createRigidArea(new Dimension(0, 95)));
		
		db1.add( sec2 );
		db1.add(Box.createRigidArea(new Dimension(10, 0)));
		
		//bottom row

		JPanel db2 = new JPanel();
		db2.setLayout( new BoxLayout( db2 , BoxLayout.LINE_AXIS) );
		db2.setBackground( Color.black);
		panel.add( db2 );
		
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
		slider.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent ce) {
	        	JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting())
                {
                	widgets.get(2).setVal(source.getValue());
                	client.updateWidget(widgets.get(2));
                }
	        }
	    });
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
		sec4.add(Box.createRigidArea(new Dimension(0, 30)));
		final JToggleButton beamswitch = new JToggleButton("OFF");
		sec4.add(beamswitch);
		beamswitch.setAlignmentX( Component.CENTER_ALIGNMENT );
		beamswitch.setMaximumSize( new Dimension(100, 135));
		beamswitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if( beamswitch.getText().equals("OFF")){
					beamswitch.setText("ON");
					widgets.get(3).setVal(1);
				}
				else{
					beamswitch.setText("OFF");
					widgets.get(3).setVal(0);
				}
				client.updateWidget(widgets.get(3));
			}
		});
		sec4.add(Box.createRigidArea(new Dimension(0, 30)));
		
		widgets = new Vector<Widget>(4);
		widgets.add(new AnyButton("Induction Iron", 2, 0, new Vector<String>(Arrays.asList("Set Induction Iron to Hot", "Set Induction Iron to Cold"))));
		widgets.add(new AnyButtonStored("Grooved Cable", 2, 0, new Vector<String>(Arrays.asList("Set Grooved Cable to Locked", "Set Grooved Cable to Unlocked"))));
		widgets.add(new Slider("Phason Collider", 0, 5, 0));
		widgets.add(new AnyButtonStored("Spectrobeam", 2, 0, new Vector<String>(Arrays.asList("Turn off Spectrobeam", "Turn on Spectrobeam"))));
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return widgets;
	}	


}

