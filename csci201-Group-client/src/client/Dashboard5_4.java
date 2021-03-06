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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Dashboard5_4 implements Dashboard{
	
	private JPanel panel;
	private Vector<Widget> widgets;
	private Client client;
	public Dashboard5_4(Client c){
		
		this.client = c;
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
		
		JLabel gridText = new JLabel("PENTOSE");
		sec1.add( gridText );
		gridText.setAlignmentX( Component.CENTER_ALIGNMENT );
		gridText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		JPanel buttonGrid = new JPanel();
		buttonGrid.setLayout( new GridLayout(2,2) );
		buttonGrid.setMaximumSize( new Dimension(180,180 ));
		ButtonGroup bg = new ButtonGroup();
		for(int i = 0; i < 4; i++){
			final JToggleButton temp = new JToggleButton( String.valueOf(i+1));
			bg.add(temp);
			buttonGrid.add(temp);
			temp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					int newval = 0;
					if(temp.getText().equals("1")){
						newval = 0;
					}
					else if(temp.getText().equals("2")){
						newval = 1;
					}
					else if(temp.getText().equals("3")){
						newval = 2;
					}
					else if(temp.getText().equals("4")){
						newval = 3;
					}
					AnyButtonStored currentWidget = (AnyButtonStored)widgets.get(0);
					currentWidget.setVal(newval);
					client.updateWidget(widgets.get(0));
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
		JLabel quantText = new JLabel("TACHYON ADAPTER");
		quantText.setAlignmentX( Component.CENTER_ALIGNMENT );
		quantText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec2.add( quantText );
		sec2.add(Box.createRigidArea(new Dimension(0, 40)));
		JButton cable = new JButton("QUELL");
		sec2.add(cable);
		cable.setAlignmentX( Component.CENTER_ALIGNMENT );
		cable.setMaximumSize( new Dimension(120, 35));
		cable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int newVal = 0;
				AnyButton currentWidget = (AnyButton)widgets.get(1);
				currentWidget.setVal(newVal);
				client.updateWidget(widgets.get(1));
			}
		});
		sec2.add(Box.createRigidArea(new Dimension(0, 25)));
		JButton synth = new JButton("SYNTHESIZE");
		sec2.add(synth);
		synth.setAlignmentX( Component.CENTER_ALIGNMENT );
		synth.setMaximumSize( new Dimension(120, 35));
		synth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int newVal = 1;
				AnyButton currentWidget = (AnyButton)widgets.get(1);
				currentWidget.setVal(newVal);
				client.updateWidget(widgets.get(1));
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
		
		sec3.add(Box.createRigidArea(new Dimension(524, 30)));
		
		JLabel phaseText = new JLabel("SUPERCALIFRAGILISTICEXPIALIDOCIOUS");
		phaseText.setAlignmentX( Component.CENTER_ALIGNMENT );
		phaseText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec3.add( phaseText );
		sec3.add(Box.createRigidArea(new Dimension(0, 70)));
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		sec3.add ( slider );
		slider.setMaximumSize( new Dimension(450, 35));
		sec3.add(Box.createRigidArea(new Dimension(0, 90)));
		slider.addChangeListener(new ChangeListener() {
	        
	        public void stateChanged(ChangeEvent ce) {
	        	JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting())
                {
                	int newval = source.getValue();
                	Slider currentwidget = (Slider)widgets.get(2);
                	currentwidget.setVal(newval);
                	client.updateWidget(widgets.get(2));
                }
	        }
	    });

		widgets = new Vector<Widget>(3);
		widgets.add(new AnyButtonStored("Pentose", 4, 0, new Vector<String>(Arrays.asList(
				"Set Pentose to 1", "Set Pentose to 2", "Set Pentose to 3", "Set Pentose to 4"))));
		widgets.add(new AnyButton("Tachyon Adapter", 2, 0, new Vector<String>(Arrays.asList("Quell the Tachyon Adapter", "Synthesize the Tachyon Adapter"))));
		widgets.add(new Slider("Supercalifragilisticexpialidocious", 0, 11, 0));
		
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