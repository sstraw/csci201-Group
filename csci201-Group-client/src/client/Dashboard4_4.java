package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Dashboard4_4 implements Dashboard{
	
	private JPanel panel;
	private Vector<Widget> widgets;
	private Client client;
	public Dashboard4_4(Client c){
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
		
		sec1.setLayout( new BoxLayout( sec1 , BoxLayout.PAGE_AXIS) );
		sec1.setBorder( BorderFactory.createLineBorder(Color.black) );
		

		sec1.add(Box.createRigidArea(new Dimension(255 , 20)));
		
		JLabel gridText = new JLabel("GRIDLOCK");
		sec1.add( gridText );
		gridText.setAlignmentX( Component.CENTER_ALIGNMENT );
		gridText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		JPanel buttonGrid = new JPanel();
		buttonGrid.setLayout( new GridLayout(2,2) );
		buttonGrid.setMaximumSize( new Dimension(180,180 ));
		for(int i = 0; i < 4; i++){
			final JButton temp = new JButton( String.valueOf(i+1));
			temp.setBackground( Color.green);
			buttonGrid.add(temp);
			temp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					int widget = Integer.parseInt(((JButton)ae.getSource()).getText());
					if(temp.getBackground().equals(Color.green) ){
						temp.setBackground( Color.red);
						widgets.get(widget).setVal(1);
					}
					else{
						temp.setBackground( Color.green);
						widgets.get(widget).setVal(0);
					}
					client.updateWidget(widgets.get(widget));
				}
			});
		}
		sec1.add(Box.createRigidArea(new Dimension(0, 10)));
		sec1.add ( buttonGrid );
		sec1.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		
		
		JPanel sec2 = new JPanel();
		sec2.setLayout( new BoxLayout( sec2 , BoxLayout.PAGE_AXIS) );
		sec2.setBorder( BorderFactory.createLineBorder(Color.black) );
		sec2.add(Box.createRigidArea(new Dimension(255, 30)));
		JLabel quantText = new JLabel("DISC LOOP");
		quantText.setAlignmentX( Component.CENTER_ALIGNMENT );
		quantText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec2.add( quantText );
		sec2.add(Box.createRigidArea(new Dimension(0, 50)));
		JButton cable = new JButton("REWIND");
		sec2.add(cable);
		cable.setAlignmentX( Component.CENTER_ALIGNMENT );
		cable.setMaximumSize( new Dimension(120, 55));
		cable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				widgets.get(0).setVal(0);
				client.updateWidget(widgets.get(0));
			}
		});
		
		sec2.add(Box.createRigidArea(new Dimension(0, 85)));
		
		db1.add( sec2 );
		db1.add(Box.createRigidArea(new Dimension(12, 0)));
		db1.add( sec1 );
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
		
		JLabel phaseText = new JLabel("MOLECULAR MAGINIFIER");
		phaseText.setAlignmentX( Component.CENTER_ALIGNMENT );
		phaseText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec3.add( phaseText );
		sec3.add(Box.createRigidArea(new Dimension(0, 70)));
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 3, 0);
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
                	widgets.get(5).setVal(source.getValue());
                	client.updateWidget(widgets.get(5));
                }
	        }
	    });
		
		db2.add(Box.createRigidArea(new Dimension(12, 0)));
		JPanel sec4 = new JPanel();
		db2.add( sec4 );
		sec4.setLayout( new BoxLayout( sec4 , BoxLayout.PAGE_AXIS) );
		sec4.setBorder( BorderFactory.createLineBorder(Color.black) );
		
		sec4.add(Box.createRigidArea(new Dimension(155, 30)));
		JLabel beamText = new JLabel("POWER-CYCLE");
		beamText.setAlignmentX( Component.CENTER_ALIGNMENT );
		beamText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec4.add( beamText );
		sec4.add(Box.createRigidArea(new Dimension(0, 35)));
		final JButton beamcable = new JButton("BOOST");
		sec4.add(beamcable);
		beamcable.setAlignmentX( Component.CENTER_ALIGNMENT );
		beamcable.setMaximumSize( new Dimension(100, 110));
		
		beamcable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				widgets.get(6).setVal(0);
				client.updateWidget(widgets.get(6));
			}
		});
		sec4.add(Box.createRigidArea(new Dimension(0, 50)));
		
		widgets = new Vector<Widget>(7);
		widgets.add(new AnyButton("Disc Loop", 1, 0, new Vector<String>(Arrays.asList("Rewind the disc loop"))));
		widgets.add(new AnyButtonStored("Gridlock 1", 2, 0, new Vector<String>(Arrays.asList("Set Gridlock 1 to Green", "Set Gridlock 1 to Red"))));
		widgets.add(new AnyButtonStored("Gridlock 2", 2, 0, new Vector<String>(Arrays.asList("Set Gridlock 2 to Green", "Set Gridlock 2 to Red"))));
		widgets.add(new AnyButtonStored("Gridlock 3", 2, 0, new Vector<String>(Arrays.asList("Set Gridlock 3 to Green", "Set Gridlock 3 to Red"))));
		widgets.add(new AnyButtonStored("Gridlock 4", 2, 0, new Vector<String>(Arrays.asList("Set Gridlock 4 to Green", "Set Gridlock 4 to Red"))));
		widgets.add(new Slider("Molecular Magnifier", 0, 4, 0));
		widgets.add(new AnyButton("Power-cycle", 1, 0, new Vector<String>(Arrays.asList("Boost the Power-cycle"))));
	}

	public JPanel getPanel() {
		return panel;
	}
	public Vector<Widget> getWidgets() {
		return widgets;
	}

}
