package client;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Dashboard1_3 implements Dashboard
{
	private JPanel panel;
	private Vector<Widget> widgets;
	private Client client;
	public Dashboard1_3(Client c)
	{
		client = c;
		panel = new JPanel();
		panel.setLayout(null);
		
		JPanel topleft = new JPanel();
		topleft.setLayout(new BoxLayout(topleft, BoxLayout.Y_AXIS));
		topleft.setBounds(0, 0, 200, 220);
		topleft.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		topleft.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel chair = new JLabel("MASSAGE CHAIR");
		topleft.add(chair);
		chair.setAlignmentX(Component.CENTER_ALIGNMENT);
		chair.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topleft.add(Box.createRigidArea(new Dimension(0, 15)));
		JSlider setting = new JSlider(JSlider.VERTICAL, 0, 4, 2);
		setting.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				if (arg0.getSource() instanceof JSlider){
					int i = ((JSlider) arg0.getSource()).getValue();
					widgets.get(0).setVal(i);
					client.updateWidget(widgets.get(0));
				}
			}
		});
		setting.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		setting.setMajorTickSpacing(1);
		setting.setPaintTicks(true);
		setting.setPaintLabels(true);
		topleft.add(setting);
		setting.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(topleft);
		
		JPanel topright = new JPanel();
		topright.setLayout(new BoxLayout(topright, BoxLayout.Y_AXIS));
		topright.setBounds(200, 0, 200, 180);
		topright.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		topright.add(Box.createRigidArea(new Dimension(0, 35)));
		JLabel hitchhiker = new JLabel("HITCHHIKER");
		hitchhiker.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topright.add(hitchhiker);
		hitchhiker.setAlignmentX(Component.CENTER_ALIGNMENT);
		topright.add(Box.createRigidArea(new Dimension(0, 20)));
		JRadioButton allow = new JRadioButton("ALLOW");
		allow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				widgets.get(1).setVal(0);
				client.updateWidget(widgets.get(1));
			}
		});
		allow.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topright.add(allow);
		allow.setAlignmentX(Component.CENTER_ALIGNMENT);
		topright.add(Box.createRigidArea(new Dimension(0, 10)));
		JRadioButton deny = new JRadioButton("DENY");
		deny.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				widgets.get(1).setVal(1);
				client.updateWidget(widgets.get(1));
			}
		});
		deny.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topright.add(deny);
		deny.setAlignmentX(Component.CENTER_ALIGNMENT);
		ButtonGroup bg = new ButtonGroup();
		bg.add(allow);
		bg.add(deny);
		
		panel.add(topright);
		
		JPanel bottomleft = new JPanel();
		bottomleft.setLayout(new BoxLayout(bottomleft, BoxLayout.Y_AXIS));
		bottomleft.setBounds(0, 220, 200, 180);
		bottomleft.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		bottomleft.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel coffee = new JLabel("COFFEEMAKER");
		coffee.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomleft.add(coffee);
		coffee.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomleft.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton brew = new JButton("BREW");
		brew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				widgets.get(2).setVal(0);
				client.updateWidget(widgets.get(2));
			}
		});
		brew.setMaximumSize(new Dimension(120, 50));
		brew.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomleft.add(brew);
		brew.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(bottomleft);
		
		JPanel bottomright = new JPanel();
		bottomright.setLayout(new BoxLayout(bottomright, BoxLayout.Y_AXIS));
		bottomright.setBounds(200, 180, 200, 220);
		bottomright.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		bottomright.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel gpa = new JLabel("GPA");
		gpa.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomright.add(gpa);
		gpa.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomright.add(Box.createRigidArea(new Dimension(0, 30)));
		String[] array = new String[] {"2.5", "3", "3.5", "4"};
		JComboBox<String> points = new JComboBox<String>(array);
		points.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof JComboBox<?>){
					int i = ((JComboBox<?>) e.getSource()).getSelectedIndex();
					widgets.get(3).setVal(i);
					client.updateWidget(widgets.get(3));
				}
			}
		});
		points.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		points.setMaximumSize(new Dimension(80, 50));
		bottomright.add(points);
		
		panel.add(bottomright);
		
		widgets = new Vector<Widget>(4);
		widgets.add(new Slider("Massage Chair", 0, 5, 2));
		widgets.add(new AnyButtonStored("Hitchhikers", 2, 0, new Vector<String>(Arrays.asList("Allow Hitchhikers", "Deny Hitchhikers"))));
		widgets.add(new AnyButton("Coffee Maker", 1, 0, new Vector<String>(Arrays.asList("Brew Coffee"))));
		widgets.add(new AnyButton("GPA", 4, 0, new Vector<String>(Arrays.asList("Set GPA to 2.5", "Set GPA to 3", "Set GPA to 3.5", "Set GPA to 4"))));
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return widgets;
	}
}
