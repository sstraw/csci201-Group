package client;
import java.awt.*;

import javax.swing.*;

public class Dashboard1_4 extends JPanel
{
	public Dashboard1_4()
	{
		setLayout(null);
		
		JPanel topleft = new JPanel();
		topleft.setLayout(new BoxLayout(topleft, BoxLayout.Y_AXIS));
		topleft.setBounds(0, 0, 200, 220);
		topleft.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		topleft.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel siren = new JLabel("ALARM SIREN");
		topleft.add(siren);
		siren.setAlignmentX(Component.CENTER_ALIGNMENT);
		siren.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topleft.add(Box.createRigidArea(new Dimension(0, 15)));
		JSlider setting = new JSlider(JSlider.VERTICAL, 0, 5, 5);
		setting.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		setting.setMajorTickSpacing(1);
		setting.setPaintTicks(true);
		setting.setPaintLabels(true);
		topleft.add(setting);
		setting.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(topleft);
		
		JPanel topright = new JPanel();
		topright.setLayout(new BoxLayout(topright, BoxLayout.Y_AXIS));
		topright.setBounds(200, 0, 200, 180);
		topright.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		topright.add(Box.createRigidArea(new Dimension(0, 35)));
		JLabel tech = new JLabel("TECHNOGRAPH");
		tech.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topright.add(tech);
		tech.setAlignmentX(Component.CENTER_ALIGNMENT);
		topright.add(Box.createRigidArea(new Dimension(0, 20)));
		JRadioButton high = new JRadioButton("HIGH");
		high.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topright.add(high);
		high.setAlignmentX(Component.CENTER_ALIGNMENT);
		topright.add(Box.createRigidArea(new Dimension(0, 10)));
		JRadioButton low = new JRadioButton("LOW");
		low.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topright.add(low);
		low.setAlignmentX(Component.CENTER_ALIGNMENT);
		ButtonGroup bg = new ButtonGroup();
		bg.add(high);
		bg.add(low);
		
		add(topright);
		
		JPanel bottomleft = new JPanel();
		bottomleft.setLayout(new BoxLayout(bottomleft, BoxLayout.Y_AXIS));
		bottomleft.setBounds(0, 220, 200, 180);
		bottomleft.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		bottomleft.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel destruct = new JLabel("SELF DESTRUCT");
		destruct.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomleft.add(destruct);
		destruct.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomleft.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton activate = new JButton("ACTIVATE");
		activate.setMaximumSize(new Dimension(120, 50));
		activate.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomleft.add(activate);
		activate.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(bottomleft);
		
		JPanel bottomright = new JPanel();
		bottomright.setLayout(new BoxLayout(bottomright, BoxLayout.Y_AXIS));
		bottomright.setBounds(200, 180, 200, 220);
		bottomright.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		bottomright.add(Box.createRigidArea(new Dimension(0, 25)));
		JLabel flamethrower = new JLabel("FLAMETHROWER");
		bottomright.add(flamethrower);
		flamethrower.setAlignmentX(Component.CENTER_ALIGNMENT);
		flamethrower.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomright.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton on = new JButton ("ON");
		on.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomright.add(on);
		on.setMaximumSize(new Dimension(140, 50));
		on.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomright.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton off = new JButton ("OFF");
		off.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomright.add(off);
		off.setAlignmentX(Component.CENTER_ALIGNMENT);
		off.setMaximumSize(new Dimension(140, 50));
		
		add(bottomright);
	}
}
