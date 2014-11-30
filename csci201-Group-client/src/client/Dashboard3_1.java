package client;
import java.awt.*;
import java.util.Vector;

import javax.swing.*;

public class Dashboard3_1 implements Dashboard
{
	private JPanel panel;
	public Dashboard3_1()
	{
		panel = new JPanel();
		panel.setLayout(null);
		
		JPanel one = new JPanel();
		one.setLayout(new BoxLayout(one, BoxLayout.Y_AXIS));
		one.setBounds(0, 0, 200, 200);
		one.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		one.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel flexbolt = new JLabel("FLEXBOLT");
		flexbolt.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		one.add(flexbolt);
		flexbolt.setAlignmentX(Component.CENTER_ALIGNMENT);
		one.add(Box.createRigidArea(new Dimension(0, 20)));
		JRadioButton connect = new JRadioButton("CONNECT");
		connect.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		one.add(connect);
		connect.setAlignmentX(Component.CENTER_ALIGNMENT);
		one.add(Box.createRigidArea(new Dimension(0, 15)));
		JRadioButton spark = new JRadioButton("SPARK");
		spark.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		one.add(spark);
		spark.setAlignmentX(Component.CENTER_ALIGNMENT);
		one.add(Box.createRigidArea(new Dimension(0, 15)));
		JRadioButton cut = new JRadioButton("CUT");
		cut.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		one.add(cut);
		cut.setAlignmentX(Component.CENTER_ALIGNMENT);
		one.add(Box.createRigidArea(new Dimension(0, 15)));
		ButtonGroup bg = new ButtonGroup();
		bg.add(connect);
		bg.add(spark);
		bg.add(cut);
		
		panel.add(one);
		
		JPanel two = new JPanel();
		two.setLayout(new BoxLayout(two, BoxLayout.Y_AXIS));
		two.setBounds(200, 0, 150, 130);
		two.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		two.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel orders = new JLabel("DIRECT ORDERS");
		orders.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		two.add(orders);
		orders.setAlignmentX(Component.CENTER_ALIGNMENT);
		two.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton disobey = new JButton("DISOBEY");
		disobey.setMaximumSize(new Dimension(120, 120));
		disobey.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		two.add(disobey);
		disobey.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(two);
		
		JPanel three = new JPanel();
		three.setLayout(new BoxLayout(three, BoxLayout.Y_AXIS));
		three.setBounds(200, 130, 150, 130);
		three.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		three.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel onions = new JLabel("ONIONS");
		onions.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		three.add(onions);
		onions.setAlignmentX(Component.CENTER_ALIGNMENT);
		three.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton caramel = new JButton("CARAMELIZE");
		caramel.setMaximumSize(new Dimension(120, 120));
		caramel.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		three.add(caramel);
		caramel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(three);
		
		JPanel four = new JPanel();
		four.setLayout(new BoxLayout(four, BoxLayout.Y_AXIS));
		four.setBounds(350, 0, 150, 260);
		four.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		four.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel frustration = new JLabel("FRUSTRATION");
		four.add(frustration);
		frustration.setAlignmentX(Component.CENTER_ALIGNMENT);
		frustration.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		four.add(Box.createRigidArea(new Dimension(0, 15)));
		JSlider settings = new JSlider(JSlider.VERTICAL, 0, 4, 2);
		settings.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		settings.setMajorTickSpacing(1);
		settings.setPaintTicks(true);
		settings.setPaintLabels(true);
		four.add(settings);
		settings.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(four);
		
		JPanel five = new JPanel();
		five.setLayout(new BoxLayout(five, BoxLayout.Y_AXIS));
		five.setBounds(0, 200, 200, 230);
		five.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		five.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel pulsing = new JLabel("PULSING RIBBONTRELLIS");
		five.add(pulsing);
		pulsing.setAlignmentX(Component.CENTER_ALIGNMENT);
		pulsing.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		five.add(Box.createRigidArea(new Dimension(0, 60)));
		String[] array = new String[] {"0", "1", "2", "3"};
		JComboBox<String> levels = new JComboBox<String>(array);
		levels.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		levels.setMaximumSize(new Dimension(80, 50));
		five.add(levels);
		
		panel.add(five);
		
		JPanel six = new JPanel();
		six.setLayout(new BoxLayout(six, BoxLayout.Y_AXIS));
		six.setBounds(200, 260, 300, 170);
		six.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		six.add(Box.createRigidArea(new Dimension(0, 35)));
		JLabel tachyon = new JLabel("TACHYON ADAPTER");
		tachyon.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		six.add(tachyon);
		tachyon.setAlignmentX(Component.CENTER_ALIGNMENT);
		six.add(Box.createRigidArea(new Dimension(0, 20)));
		JPanel side = new JPanel();
		side.setLayout(new BoxLayout(side, BoxLayout.X_AXIS));
		JButton num0 = new JButton ("-1");
		num0.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		side.add(num0);
		num0.setMaximumSize(new Dimension(95, 50));
		side.add(Box.createRigidArea(new Dimension(10, 0)));
		JButton num1 = new JButton ("0");
		num1.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		side.add(num1);
		num1.setMaximumSize(new Dimension(50, 50));
		side.add(Box.createRigidArea(new Dimension(10, 0)));
		JButton num2 = new JButton ("1");
		num2.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		side.add(num2);
		num2.setMaximumSize(new Dimension(95, 50));
		six.add(side);
		
		panel.add(six);
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return null;
	}
}
