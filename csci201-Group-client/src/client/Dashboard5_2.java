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

public class Dashboard5_2 implements Dashboard{

private JPanel panel;
	private Vector<Widget> widgets;
	public Dashboard5_2(Client c){
		
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
			
//			temp.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent ae) {
//					command.setText("SELECT LOGIC CONTROL BOARD " +  temp.getText() );	
//				}
//			});
		}
		sec1.add(Box.createRigidArea(new Dimension(0, 10)));
		sec1.add ( buttonGrid );
		sec1.add(Box.createRigidArea(new Dimension(0, 10)));
		db1.add(Box.createRigidArea(new Dimension(12, 0)));
		
		//bottom row
		JPanel db2 = new JPanel();
		db2.setLayout( new BoxLayout( db2 , BoxLayout.LINE_AXIS) );
		db2.setBackground( Color.black);
		panel.add( db2 );
		
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
	        
			public void stateChanged(ChangeEvent ce) {
	        	JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting())
                {
                	int newval = source.getValue();
                	Slider currentwidget = (Slider)widgets.get(1);
                	currentwidget.setVal(newval);
                }
	        }
	    });
		
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
				int newVal = 0;
				AnyButton currentWidget = (AnyButton)widgets.get(2);
				currentWidget.setVal(newVal);
			}
		});
		sec4.add(Box.createRigidArea(new Dimension(0, 20)));
		final JButton jiggle = new JButton("JIGGLE");
		sec4.add(jiggle);
		jiggle.setAlignmentX( Component.CENTER_ALIGNMENT );
		jiggle.setMaximumSize( new Dimension(100, 50));
		
		jiggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int newVal = 0;
				AnyButton currentWidget = (AnyButton)widgets.get(3);
				currentWidget.setVal(newVal);
			}
		});
		
		sec4.add(Box.createRigidArea(new Dimension(0, 24)));
		
		widgets = new Vector<Widget>(11);
		for (int i = 1; i < 10; i++){
			widgets.add(new AnyButtonStored(String.format("Logic Control Board %d", i), 2, 0,
					    new Vector<String>(Arrays.asList(String.format("Set Logic Control Board %d to not pressed", i), (String.format("Set Logic Control Board %d to pressed", i))))));
		}
		widgets.add(new AnyButton("Emergency Whittler", 2, 0, new Vector<String>(Arrays.asList("Baste the Emergency Whittler", "Juggle the Emergency Whittler"))));
		widgets.add(new Slider("Epsilon Trap", 0, 5, 0));
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



