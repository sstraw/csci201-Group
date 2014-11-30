package client;


import java.awt.*;
import java.util.Vector;

import javax.swing.*;

public class Dashboard1_3 implements Dashboard
{
	private JPanel panel;
	public Dashboard1_3(Client c)
	{
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
		allow.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topright.add(allow);
		allow.setAlignmentX(Component.CENTER_ALIGNMENT);
		topright.add(Box.createRigidArea(new Dimension(0, 10)));
		JRadioButton deny = new JRadioButton("DENY");
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
		points.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		points.setMaximumSize(new Dimension(80, 50));
		bottomright.add(points);
		
		panel.add(bottomright);
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
