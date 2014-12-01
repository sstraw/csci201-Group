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
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Dashboard4_3  implements Dashboard{
	private JPanel panel;
	private Vector<Widget> widgets;
	private Client client;
	public Dashboard4_3(Client c){
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
		
		JLabel ironText = new JLabel("SHIP'S LOG");
		sec1.add( ironText );
		ironText.setAlignmentX( Component.CENTER_ALIGNMENT );
		ironText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec1.add(Box.createRigidArea(new Dimension(0, 50)));
		JButton hot = new JButton ( "UPDATE" );
		hot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				widgets.get(0).setVal(0);
				client.updateWidget(widgets.get(0));
			}
		});
		
		sec1.add( hot );
		hot.setMaximumSize( new Dimension(120, 55));
		hot.setAlignmentX( Component.CENTER_ALIGNMENT );
		
		sec1.add(Box.createRigidArea(new Dimension(0, 85)));
		
		db1.add(Box.createRigidArea(new Dimension(12, 0)));
		
		JPanel sec2 = new JPanel();
		sec2.setLayout( new BoxLayout( sec2 , BoxLayout.PAGE_AXIS) );
		sec2.setBorder( BorderFactory.createLineBorder(Color.black) );
		sec2.add(Box.createRigidArea(new Dimension(255, 30)));
		JLabel cableText = new JLabel("GRANULAR PUTTY");
		cableText.setAlignmentX( Component.CENTER_ALIGNMENT );
		cableText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec2.add( cableText );
		sec2.add(Box.createRigidArea(new Dimension(0, 20)));
		final JButton zero = new JButton("0");
		sec2.add(zero);
		zero.setAlignmentX( Component.CENTER_ALIGNMENT );
		zero.setMaximumSize( new Dimension(45, 45));
		zero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				widgets.get(1).setVal(0);
				client.updateWidget(widgets.get(1));
			}
		});
		final JButton one = new JButton("1");
		sec2.add(one);
		one.setAlignmentX( Component.CENTER_ALIGNMENT );
		one.setMaximumSize( new Dimension(45, 45));
		one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				widgets.get(1).setVal(1);
				client.updateWidget(widgets.get(1));
			}
		});
		
		final JButton two = new JButton("2");
		sec2.add(two);
		two.setAlignmentX( Component.CENTER_ALIGNMENT );
		two.setMaximumSize( new Dimension(45, 45));
		two.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				widgets.get(1).setVal(2);
				client.updateWidget(widgets.get(1));
			}
		});
		
		sec2.add(Box.createRigidArea(new Dimension(0, 35)));
		
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
		db2.add( sec3 );
		JLabel phaseText = new JLabel("SUCTIONGAUGE");
		phaseText.setAlignmentX( Component.CENTER_ALIGNMENT );
		phaseText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec3.add( phaseText );
		sec3.add(Box.createRigidArea(new Dimension(0, 70)));
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
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
		
		
		JPanel sec4 = new JPanel();
		
		db2.add(Box.createRigidArea(new Dimension(12, 0)));
		db2.add( sec4 );
		
		sec4.setLayout( new BoxLayout( sec4 , BoxLayout.PAGE_AXIS) );
		sec4.setBorder( BorderFactory.createLineBorder(Color.black) );
		
		sec4.add(Box.createRigidArea(new Dimension(155, 30)));
		JLabel beamText = new JLabel("BETA ALERT");
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
		widgets.add(new AnyButton("Ship's Log", 1, 0, new Vector<String>(Arrays.asList("Update the Ship's Log"))));
		widgets.add(new AnyButton("Granular Putty", 3, 0, new Vector<String>(Arrays.asList("Set Granular Putty to 0", "Set Granular Putty to 1", "Set Granular Putty to 2"))));
		widgets.add(new Slider("Suction Gauge", 0, 6, 0));
		widgets.add(new AnyButtonStored("Beta Alert", 2, 0, new Vector<String>(Arrays.asList("Turn off Beta Alert", "Turn on Beta Alert"))));
		
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return widgets;
	}	


}