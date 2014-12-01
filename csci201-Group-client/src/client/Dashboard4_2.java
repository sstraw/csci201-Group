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

public class Dashboard4_2 implements Dashboard{
	private JPanel panel;
	private Vector<Widget> widgets;
	private Client client;
	public Dashboard4_2(Client c){
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
		
		JLabel ironText = new JLabel("SUPERSTICIOUS SALAD");
		sec1.add( ironText );
		ironText.setAlignmentX( Component.CENTER_ALIGNMENT );
		ironText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec1.add(Box.createRigidArea(new Dimension(0, 40)));
		JButton hot = new JButton ( "RANCH" );
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
		JButton cold = new JButton ( "ITALIAN" );
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
		JLabel cableText = new JLabel("CROWLEY CLAW");
		cableText.setAlignmentX( Component.CENTER_ALIGNMENT );
		cableText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec2.add( cableText );
		sec2.add(Box.createRigidArea(new Dimension(0, 60)));
		final JToggleButton cable = new JToggleButton("UNHOOKED");
		sec2.add(cable);
		cable.setAlignmentX( Component.CENTER_ALIGNMENT );
		cable.setMaximumSize( new Dimension(120, 35));
		cable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if( cable.getText().equals("HOOKED")){
					cable.setText("UNHOOKED");
					widgets.get(1).setVal(1);
				}
				else{
					cable.setText("HOOKED");
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
		//db2.add( sec3 );
		sec3.setLayout( new BoxLayout( sec3 , BoxLayout.PAGE_AXIS) );
		sec3.setBorder( BorderFactory.createLineBorder(Color.black) );
		sec3.add(Box.createRigidArea(new Dimension(355, 30)));
		
		JLabel phaseText = new JLabel("PHYLON SAUCER");
		phaseText.setAlignmentX( Component.CENTER_ALIGNMENT );
		phaseText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec3.add( phaseText );
		sec3.add(Box.createRigidArea(new Dimension(0, 70)));
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 3, 0);
		slider.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent ce) {
	        	JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting())
                {
                	widgets.get(3).setVal(source.getValue());
                	client.updateWidget(widgets.get(3));
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
		db2.add( sec4 );
		db2.add(Box.createRigidArea(new Dimension(12, 0)));
		db2.add( sec3 );
		
		sec4.setLayout( new BoxLayout( sec4 , BoxLayout.PAGE_AXIS) );
		sec4.setBorder( BorderFactory.createLineBorder(Color.black) );
		
		sec4.add(Box.createRigidArea(new Dimension(155, 30)));
		JLabel beamText = new JLabel("RAY HUE");
		beamText.setAlignmentX( Component.CENTER_ALIGNMENT );
		beamText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec4.add( beamText );
		sec4.add(Box.createRigidArea(new Dimension(0, 30)));
		final JButton beamswitch = new JButton();
		sec4.add(beamswitch);
		beamswitch.setAlignmentX( Component.CENTER_ALIGNMENT );
		beamswitch.setBackground(Color.green);
		beamswitch.setMaximumSize( new Dimension(100, 135));
		beamswitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if( beamswitch.getBackground().equals(Color.green)){
					beamswitch.setBackground( Color.blue);
					widgets.get(2).setVal(1);
				}
				else if(beamswitch.getBackground().equals(Color.blue)){
					beamswitch.setBackground( Color.red);
					widgets.get(2).setVal(2);
				}
				else{
					beamswitch.setBackground( Color.green);
					widgets.get(2).setVal(0);
				}
				client.updateWidget(widgets.get(2));
			}
		});
		sec4.add(Box.createRigidArea(new Dimension(0, 30)));
		
		widgets = new Vector<Widget>(4);
		widgets.add(new AnyButton("Superstitious Salad", 2, 0, new Vector<String>(Arrays.asList("Put Ranch on the Superstitious Salad", "Put Italian on the Superstitious Salad"))));
		widgets.add(new AnyButtonStored("Crowley Claw", 2, 0, new Vector<String>(Arrays.asList("Unhook the Crowley Claw", "Hook the Crowley Claw"))));
		widgets.add(new AnyButtonStored("Ray Hue", 3, 0, new Vector<String>(Arrays.asList("Set the Ray Hue to Green", "Set the Ray Hue to Blue", "Set the Ray Hue to Red"))));
		widgets.add(new Slider("Phylon Saucer", 0, 4, 0));
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		// TODO Auto-generated method stub
		return widgets;
	}	


}