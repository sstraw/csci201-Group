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

public class Dashboard5_3 implements Dashboard{
	private JPanel panel;
	private Vector<Widget> widgets;
	public Dashboard5_3(Client c){
		
		panel = new JPanel();
		panel.setLayout( new GridLayout(1,1) );
		
		
		
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
		sec1.setBackground( Color.black);
		sec1.add(Box.createRigidArea(new Dimension(315, 15)));
		
		
		JPanel sec2 = new JPanel();
		sec2.setLayout( new BoxLayout( sec2 , BoxLayout.PAGE_AXIS) );
		sec2.setBorder( BorderFactory.createLineBorder(Color.black) );
		sec2.add(Box.createRigidArea(new Dimension(255, 30)));
		JLabel quantText = new JLabel("AIR ENGINES");
		quantText.setAlignmentX( Component.CENTER_ALIGNMENT );
		quantText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec2.add( quantText );
		sec2.add(Box.createRigidArea(new Dimension(0, 30)));
		JButton cable = new JButton("BLIGE");
		sec2.add(cable);
		cable.setAlignmentX( Component.CENTER_ALIGNMENT );
		cable.setMaximumSize( new Dimension(120, 75));
//		cable.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				command.setText( "BLIGE AIR ENGINES");
//			}
//		});
		sec2.add(Box.createRigidArea(new Dimension(0, 30)));
		JButton plan = new JButton("PLAN");
		sec2.add(plan);
		plan.setAlignmentX( Component.CENTER_ALIGNMENT );
		plan.setMaximumSize( new Dimension(120, 75));
//		plan.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				command.setText( "PLAN AIR ENGINES");
//			}
//		});
		sec2.add(Box.createRigidArea(new Dimension(0, 30)));
		JButton humid = new JButton("HUMIDIFY");
		sec2.add(humid);
		humid.setAlignmentX( Component.CENTER_ALIGNMENT );
		humid.setMaximumSize( new Dimension(120, 75));
//		humid.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				command.setText( "HUMIDIFY AIR ENGINES");
//			}
//		});
		sec2.add(Box.createRigidArea(new Dimension(0, 30)));
		
		
		sec1.add(sec2);
		
		JPanel sec4 = new JPanel();
		sec4.setLayout( new BoxLayout( sec4 , BoxLayout.PAGE_AXIS) );
		sec4.setBorder( BorderFactory.createLineBorder(Color.black) );
		sec4.add(Box.createRigidArea(new Dimension(255, 30)));
		JLabel quantText2 = new JLabel("SHIFTSANITIZER");
		quantText2.setAlignmentX( Component.CENTER_ALIGNMENT );
		quantText2.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec4.add( quantText2 );
		sec4.add(Box.createRigidArea(new Dimension(0, 30)));
		JButton cable2 = new JButton("UNCORK");
		sec4.add(cable2);
		cable2.setAlignmentX( Component.CENTER_ALIGNMENT );
		cable2.setMaximumSize( new Dimension(120, 35));
//		cable2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				command.setText( "UNCORK SHIFTSANITIZER");
//			}
//		});
		sec4.add(Box.createRigidArea(new Dimension(0, 30)));
		JButton cable3 = new JButton("ENGORGE");
		sec4.add(cable3);
		cable3.setAlignmentX( Component.CENTER_ALIGNMENT );
		cable3.setMaximumSize( new Dimension(120, 35));
//		cable3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				command.setText( "ENGORGE SHIFTSANITIZER");
//			}
//		});
		sec4.add(Box.createRigidArea(new Dimension(0, 30)));
		
		sec1.add(Box.createRigidArea(new Dimension( 0 , 30)));
		
		sec1.add(sec4);
		sec1.add(Box.createRigidArea(new Dimension( 0 , 15)));
		
		db1.add(Box.createRigidArea(new Dimension(5, 0)));
		
		
		//bottom row

		
		db1.add(Box.createRigidArea(new Dimension(12, 0)));
		JPanel sec3 = new JPanel();
		
		sec3.setLayout( new BoxLayout( sec3 , BoxLayout.PAGE_AXIS) );
		sec3.setBorder( BorderFactory.createLineBorder(Color.black) );
		
		sec3.add(Box.createRigidArea(new Dimension( 190 , 30)));
		
		JLabel phaseText = new JLabel("EAVSTROUGH");
		phaseText.setAlignmentX( Component.CENTER_ALIGNMENT );
		phaseText.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sec3.add( phaseText );
		sec3.add(Box.createRigidArea(new Dimension(0, 70)));
		JSlider slider = new JSlider(JSlider.VERTICAL, 0, 5, 0);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		sec3.add ( slider );
		slider.setMaximumSize( new Dimension(40, 300));
		sec3.add(Box.createRigidArea(new Dimension(0, 90)));
//		slider.addChangeListener(new ChangeListener() {
//	        @Override
//	        public void stateChanged(ChangeEvent ce) {
//	        	JSlider source = (JSlider)ce.getSource();
//                if(!source.getValueIsAdjusting())
//                {
//                	//System.out.println( "PHASON COLLIDER SET TO " +  source.getValue() );
//                	command.setText( "SET EAVSTROUGH TO " + source.getValue() );
//                }
//	        }
//	    });
		
		//defribilator
		//hahahaha
		//Supercalifragilisticexpialidocious
		
		
	
		
		db1.add( sec3 );
		
		widgets = new Vector<Widget>(3);
		widgets.add(new AnyButton("Air Engines", 3, 0, new Vector<String>(Arrays.asList("Bilge the Air Engines", "Plan the Air Engines", "Humidify the Air Engines"))));
		widgets.add(new AnyButton("Shift Sanitizer", 2, 0, new Vector<String>(Arrays.asList("Uncork the Shift Sanitizer", "Engorge the Shift Sanitizer"))));
		widgets.add(new Slider("Eavstrough", 0, 6, 0));
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return widgets;
	}

}


