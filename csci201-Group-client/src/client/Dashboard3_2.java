package client;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;

public class Dashboard3_2 implements Dashboard
{
	private JPanel panel;
	private Vector<Widget> widgets;
	public Dashboard3_2(Client c)
	{
		panel = new JPanel();
		panel.setLayout(null);
		
		JPanel one = new JPanel();
		one.setLayout(new BoxLayout(one, BoxLayout.Y_AXIS));
		one.setBounds(0, 0, 200, 200);
		one.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		one.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel choke = new JLabel("CHOKEFULORUM");
		choke.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		one.add(choke);
		choke.setAlignmentX(Component.CENTER_ALIGNMENT);
		one.add(Box.createRigidArea(new Dimension(0, 40)));
		String[] array = new String[] {"0", "1", "2", "3"};
		JComboBox<String> levels = new JComboBox<String>(array);
		levels.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		levels.setMaximumSize(new Dimension(80, 50));
		one.add(levels);
		
		panel.add(one);
		
		JPanel two = new JPanel();
		two.setLayout(new BoxLayout(two, BoxLayout.Y_AXIS));
		two.setBounds(200, 0, 300, 150);
		two.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		two.add(Box.createRigidArea(new Dimension(0, 25)));
		JLabel cyclone = new JLabel("POWERCYCLONE");
		cyclone.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		two.add(cyclone);
		cyclone.setAlignmentX(Component.CENTER_ALIGNMENT);
		two.add(Box.createRigidArea(new Dimension(0, 15)));
		JRadioButton kick = new JRadioButton("KICK");
		kick.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		two.add(kick);
		kick.setAlignmentX(Component.CENTER_ALIGNMENT);
		two.add(Box.createRigidArea(new Dimension(0, 10)));
		JRadioButton release = new JRadioButton("RELEASE");
		release.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		two.add(release);
		release.setAlignmentX(Component.CENTER_ALIGNMENT);
		ButtonGroup bg = new ButtonGroup();
		bg.add(kick);
		bg.add(release);
		
		panel.add(two);
		
		JPanel three = new JPanel();
		three.setLayout(new BoxLayout(three, BoxLayout.Y_AXIS));
		three.setBounds(0, 200, 200, 230);
		three.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		three.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel newton = new JLabel("NEWTONIAN PHOTOMIST");
		three.add(newton);
		newton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newton.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		three.add(Box.createRigidArea(new Dimension(0, 15)));
		JSlider settings = new JSlider(JSlider.VERTICAL, 0, 3, 0);
		settings.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		settings.setMajorTickSpacing(1);
		settings.setPaintTicks(true);
		settings.setPaintLabels(true);
		three.add(settings);
		settings.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(three);
		
		JPanel four = new JPanel();
		four.setLayout(new BoxLayout(four, BoxLayout.Y_AXIS));
		four.setBounds(200, 150, 300, 140);
		four.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		four.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel barometer = new JLabel("FLIGHT BAROMETER");
		four.add(barometer);
		barometer.setAlignmentX(Component.CENTER_ALIGNMENT);
		barometer.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		four.add(Box.createRigidArea(new Dimension(0, 15)));
		JSlider setting = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
		setting.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		setting.setMajorTickSpacing(1);
		setting.setPaintTicks(true);
		setting.setPaintLabels(true);
		four.add(setting);
		setting.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(four);
		
		JPanel five = new JPanel();
		five.setLayout(new BoxLayout(five, BoxLayout.Y_AXIS));
		five.setBounds(200, 290, 150, 140);
		five.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		five.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel space = new JLabel("SPACEHORN");
		space.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		five.add(space);
		space.setAlignmentX(Component.CENTER_ALIGNMENT);
		five.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton honk = new JButton ("HONK");
		honk.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		five.add(honk);
		honk.setAlignmentX(Component.CENTER_ALIGNMENT);
		honk.setMaximumSize(new Dimension(80, 80));
		
		panel.add(five);
		
		JPanel six = new JPanel();
		six.setLayout(new BoxLayout(six, BoxLayout.Y_AXIS));
		six.setBounds(350, 290, 150, 140);
		six.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		six.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton quell = new JButton ("QUELL");
		quell.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		six.add(quell);
		quell.setAlignmentX(Component.CENTER_ALIGNMENT);
		quell.setMaximumSize(new Dimension(80, 80));
		six.add(Box.createRigidArea(new Dimension(0, 10)));
		JLabel robot = new JLabel("ROBOT UPRISING");
		robot.setAlignmentX(Component.CENTER_ALIGNMENT);
		robot.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		six.add(robot);
		
		panel.add(six);
		
		widgets = new Vector<Widget>(6);
		widgets.add(new Slider("Chokefulorum", 0, 4, 0));
		widgets.add(new Slider("Newtonian Photomist", 0, 4, 0));
		widgets.add(new AnyButtonStored("Powercyclone", 2, 0, new Vector<String>(Arrays.asList("Kick Powercyclone", "Release Powercyclone"))));
		widgets.add(new Slider("Flight Barometer", 0, 6, 0));
		widgets.add(new AnyButton("Spacehorn", 1, 0, new Vector<String>(Arrays.asList("Honk the Spacehorn"))));
		widgets.add(new AnyButton("Robot Uprising", 1, 0, new Vector<String>(Arrays.asList("Quell the Robot Uprising"))));
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return widgets;
	}
}
