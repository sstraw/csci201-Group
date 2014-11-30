package client;
import java.awt.*;
import java.util.Vector;

import javax.swing.*;

public class Dashboard2_3 implements Dashboard
{
	private JPanel panel;
	public Dashboard2_3()
	{
		panel = new JPanel();
		panel.setLayout(null);
		
		JPanel one = new JPanel();
		one.setLayout(new BoxLayout(one, BoxLayout.Y_AXIS));
		one.setBounds(0, 0, 200, 200);
		one.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		one.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel solution = new JLabel("SLOPING SOLUTION");
		solution.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		one.add(solution);
		solution.setAlignmentX(Component.CENTER_ALIGNMENT);
		one.add(Box.createRigidArea(new Dimension(0, 40)));
		String[] array = new String[] {"0", "1", "2", "3"};
		JComboBox<String> levels = new JComboBox<String>(array);
		levels.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		levels.setMaximumSize(new Dimension(80, 50));
		one.add(levels);
		
		panel.add(one);
		
		JPanel two = new JPanel();
		two.setLayout(new BoxLayout(two, BoxLayout.Y_AXIS));
		two.setBounds(200, 0, 300, 150);
		two.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		two.add(Box.createRigidArea(new Dimension(0, 25)));
		JLabel dish = new JLabel("MONODISH");
		dish.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		two.add(dish);
		dish.setAlignmentX(Component.CENTER_ALIGNMENT);
		two.add(Box.createRigidArea(new Dimension(0, 15)));
		JRadioButton run = new JRadioButton("RUN");
		run.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		two.add(run);
		run.setAlignmentX(Component.CENTER_ALIGNMENT);
		two.add(Box.createRigidArea(new Dimension(0, 10)));
		JRadioButton analysis = new JRadioButton("ANALYZE");
		analysis.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		two.add(analysis);
		analysis.setAlignmentX(Component.CENTER_ALIGNMENT);
		ButtonGroup bg = new ButtonGroup();
		bg.add(run);
		bg.add(analysis);
		
		panel.add(two);
		
		JPanel three = new JPanel();
		three.setLayout(new BoxLayout(three, BoxLayout.Y_AXIS));
		three.setBounds(0, 200, 200, 230);
		three.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		three.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel iron = new JLabel("INDUCTION IRON");
		three.add(iron);
		iron.setAlignmentX(Component.CENTER_ALIGNMENT);
		iron.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		three.add(Box.createRigidArea(new Dimension(0, 15)));
		JSlider settings = new JSlider(JSlider.VERTICAL, 0, 3, 0);
		settings.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
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
		JLabel exhaust = new JLabel("FLANGE EXHAUST");
		four.add(exhaust);
		exhaust.setAlignmentX(Component.CENTER_ALIGNMENT);
		exhaust.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		four.add(Box.createRigidArea(new Dimension(0, 15)));
		JSlider setting = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
		setting.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		setting.setMajorTickSpacing(1);
		setting.setPaintTicks(true);
		setting.setPaintLabels(true);
		four.add(setting);
		setting.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(four);
		
		JPanel five = new JPanel();
		five.setLayout(new BoxLayout(five, BoxLayout.X_AXIS));
		five.setBounds(200, 290, 300, 140);
		five.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		five.add(Box.createRigidArea(new Dimension(30, 0)));
		JButton yes = new JButton ("");
		yes.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		five.add(yes);
		yes.setMaximumSize(new Dimension(100, 100));
		five.add(Box.createRigidArea(new Dimension(30, 0)));
		JLabel infratoxin = new JLabel("INFRATOXIN");
		infratoxin.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		five.add(infratoxin);
		
		panel.add(five);
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		// TODO Auto-generated method stub
		return null;
	}
}
