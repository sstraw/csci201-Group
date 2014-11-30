package client;
import java.awt.*;
import java.util.Vector;

import javax.swing.*;

public class Dashboard3_3 implements Dashboard
{
	private JPanel panel;
	private Vector<Widget> widgets;
	public Dashboard3_3(Client c)
	{
		panel = new JPanel();
		panel.setLayout(null);
		
		JPanel one = new JPanel();
		one.setLayout(new BoxLayout(one, BoxLayout.Y_AXIS));
		one.setBounds(0, 0, 180, 200);
		one.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		one.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel slime = new JLabel("SUBSLIME");
		one.add(slime);
		slime.setAlignmentX(Component.CENTER_ALIGNMENT);
		slime.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		one.add(Box.createRigidArea(new Dimension(0, 10)));
		JSlider setting = new JSlider(JSlider.VERTICAL, 0, 4, 2);
		setting.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		setting.setMajorTickSpacing(1);
		setting.setPaintTicks(true);
		setting.setPaintLabels(true);
		one.add(setting);
		setting.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(one);
		
		JPanel two = new JPanel();
		two.setLayout(new BoxLayout(two, BoxLayout.Y_AXIS));
		two.setBounds(180, 0, 320, 200);
		two.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		two.add(Box.createRigidArea(new Dimension(0, 25)));
		JLabel gigamill = new JLabel("GIGAMILL");
		gigamill.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		two.add(gigamill);
		gigamill.setAlignmentX(Component.CENTER_ALIGNMENT);
		two.add(Box.createRigidArea(new Dimension(0, 20)));
		JPanel sidetop = new JPanel();
		sidetop.setLayout(new BoxLayout(sidetop, BoxLayout.X_AXIS));
		JButton num0 = new JButton ("0");
		num0.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		sidetop.add(num0);
		num0.setMaximumSize(new Dimension(100, 50));
		sidetop.add(Box.createRigidArea(new Dimension(10, 0)));
		JButton num1 = new JButton ("1");
		num1.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		sidetop.add(num1);
		num1.setMaximumSize(new Dimension(100, 50));
		two.add(sidetop);
		two.add(Box.createRigidArea(new Dimension(0, 10)));
		JPanel sidebottom = new JPanel();
		sidebottom.setLayout(new BoxLayout(sidebottom, BoxLayout.X_AXIS));
		JButton num2 = new JButton ("2");
		num2.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		sidebottom.add(num2);
		num2.setMaximumSize(new Dimension(100, 50));
		sidebottom.add(Box.createRigidArea(new Dimension(10, 0)));
		JButton num3 = new JButton ("3");
		num3.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		sidebottom.add(num3);
		num3.setMaximumSize(new Dimension(100, 50));
		two.add(sidebottom);
		
		panel.add(two);
		
		JPanel three = new JPanel();
		three.setLayout(new BoxLayout(three, BoxLayout.Y_AXIS));
		three.setBounds(0, 200, 200, 230);
		three.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		three.add(Box.createRigidArea(new Dimension(0, 35)));
		JLabel miller = new JLabel("MILLER MONORAY");
		three.add(miller);
		miller.setAlignmentX(Component.CENTER_ALIGNMENT);
		miller.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		three.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton aim = new JButton ("AIM");
		aim.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		three.add(aim);
		aim.setMaximumSize(new Dimension(120, 50));
		aim.setAlignmentX(Component.CENTER_ALIGNMENT);
		three.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton fire = new JButton ("FIRE");
		fire.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		three.add(fire);
		fire.setAlignmentX(Component.CENTER_ALIGNMENT);
		fire.setMaximumSize(new Dimension(120, 50));
		
		panel.add(three);
		
		JPanel four = new JPanel();
		four.setLayout(new BoxLayout(four, BoxLayout.Y_AXIS));
		four.setBounds(200, 200, 150, 115);
		four.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		four.add(Box.createRigidArea(new Dimension(0, 10)));
		JLabel geiger = new JLabel("GEIGER POWERLANTERN");
		four.add(geiger);
		geiger.setAlignmentX(Component.CENTER_ALIGNMENT);
		geiger.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		four.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton light = new JButton ("LIGHT");
		light.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		four.add(light);
		light.setAlignmentX(Component.CENTER_ALIGNMENT);
		light.setMaximumSize(new Dimension(100, 80));
		
		panel.add(four);
		
		JPanel five = new JPanel();
		five.setLayout(new BoxLayout(five, BoxLayout.Y_AXIS));
		five.setBounds(350, 200, 150, 115);
		five.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		five.add(Box.createRigidArea(new Dimension(0, 10)));
		JLabel transmission = new JLabel("TRANSMISSION");
		five.add(transmission);
		transmission.setAlignmentX(Component.CENTER_ALIGNMENT);
		transmission.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		five.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton decrypt = new JButton ("DECRYPT");
		decrypt.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		five.add(decrypt);
		decrypt.setAlignmentX(Component.CENTER_ALIGNMENT);
		decrypt.setMaximumSize(new Dimension(100, 80));
		
		panel.add(five);
		
		JPanel six = new JPanel();
		six.setLayout(new BoxLayout(six, BoxLayout.X_AXIS));
		six.setBounds(200, 315, 300, 115);
		six.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		six.add(Box.createRigidArea(new Dimension(40, 0)));
		String[] array = new String[] {"0", "1", "2", "3"};
		JComboBox<String> levels = new JComboBox<String>(array);
		levels.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		levels.setMaximumSize(new Dimension(80, 50));
		six.add(levels);
		six.add(Box.createRigidArea(new Dimension(40, 0)));
		JLabel beacon = new JLabel("B-BEACON");
		beacon.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		six.add(beacon);
		
		panel.add(six);
		widgets = new Vector<Widget>(6);
		widgets.add(new Slider("Subslime", 0, 5, 2));
		widgets.add(new Gigamill(0));
		widgets.add(new MillerMonoray(0));
		widgets.add(new GeigerPowerlantern());
		widgets.add(new Transmission());
		widgets.add(new Slider("B-Beacon", 0, 4, 0));
	}

	public JPanel getPanel() {
		return panel;
	}

	@Override
	public Vector<Widget> getWidgets() {
		return widgets;
	}
}
