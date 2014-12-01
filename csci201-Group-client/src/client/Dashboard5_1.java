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

public class Dashboard5_1  implements Dashboard{
	
	private JPanel panel;
	private Vector<Widget> widgets;
	public Dashboard5_1(Client c){
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
		sec1.add(Box.createRigidArea(new Dimension(255 , 20)));
		
		JLabel gridText = new JLabel("TRIPLE GRID PLEXER");
		sec1.add( gridText );
		gridText.setAlignmentX( Component.CENTER_ALIGNMENT );
		gridText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		JPanel buttonGrid = new JPanel();
		buttonGrid.setLayout( new GridLayout(3,3) );
		buttonGrid.setMaximumSize( new Dimension(180,180 ));
		for(int i =0; i < 9; i++){
			final JButton temp = new JButton( String.valueOf(i+1));
			temp.setBackground( Color.green);
			buttonGrid.add(temp);
			temp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					if(temp.getBackground().equals(Color.green) ){
						temp.setBackground( Color.red);
					}
					else{
						temp.setBackground( Color.green);
					}
					int newVal = 0;
					AnyButton currentWidget = (AnyButton)widgets.get(0);
					currentWidget.setVal(newVal);
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
				int newVal = 0;
				AnyButton currentWidget = (AnyButton)widgets.get(1);
				currentWidget.setVal(newVal);
			}
		});
		sec2.add(Box.createRigidArea(new Dimension(0, 25)));
		JButton synth = new JButton("SYNTHESIZE");
		sec2.add(synth);
		synth.setAlignmentX( Component.CENTER_ALIGNMENT );
		synth.setMaximumSize( new Dimension(120, 35));
		synth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int newVal = 0;
				AnyButton currentWidget = (AnyButton)widgets.get(2);
				currentWidget.setVal(newVal);
			}
		});
		
		sec2.add(Box.createRigidArea(new Dimension(0, 55)));
		
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
		slider.addChangeListener(new ChangeListener() {
	        
	        public void stateChanged(ChangeEvent ce) {
	        	JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting())
                {
                	int newval = source.getValue();
                	Slider currentwidget = (Slider)widgets.get(3);
                	currentwidget.setVal(newval);
                }
	        }
	    });
		
		
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
					command.setText("SET MILK MOLECULIZER TO CLEAN");
				}
				else{
					beamcable.setText("DIRTY");
					command.setText("SET MILK MOLECULIZER TO DIRTY");
				}
			}
		});
		sec4.add(Box.createRigidArea(new Dimension(0, 30)));
		
		widgets = new Vector<Widget>(12);
		for (int i = 1; i < 10; i++){
			widgets.add(new AnyButtonStored(String.format("Triple Grid Plexer %d", i), 2, 0,
					    new Vector<String>(Arrays.asList(String.format("Set Triple Grid Plexer %d to Red", i), (String.format("Set Triple Grid Plexer %d to Green", i))))));
		}
		widgets.add(new AnyButton("Quantum Omegifier", 2, 0, new Vector<String>(Arrays.asList("Disperse the Quantum Omegifier", "Synthesize the Quantum Omegifier"))));
		widgets.add(new Slider("Elastic Illusioner", 0, 11, 0));
		widgets.add(new AnyButtonStored("Milk Moleculizer", 2, 0, new Vector<String>(Arrays.asList("Switch Milk Moleculizer to Dirty", "Switch Milk Moleculizer to Clean"))));
	}

	public JPanel getPanel() {
		return panel;
	}

	@Override
	public Vector<Widget> getWidgets() {
		// TODO Auto-generated method stub
		return null;
	}

}


