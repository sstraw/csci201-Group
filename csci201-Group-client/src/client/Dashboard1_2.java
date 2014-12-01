package client;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;

public class Dashboard1_2 implements Dashboard
{
	private JPanel panel;
	private Vector<Widget> widgets;
	
	public Dashboard1_2(Client c)
	{
		panel = new JPanel();
		panel.setLayout(null);
		
		JPanel topleft = new JPanel();
		topleft.setLayout(new BoxLayout(topleft, BoxLayout.Y_AXIS));
		topleft.setBounds(0, 0, 200, 200);
		topleft.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		topleft.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel gate = new JLabel("SPACE GATE");
		topleft.add(gate);
		gate.setAlignmentX(Component.CENTER_ALIGNMENT);
		gate.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topleft.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton open = new JButton ("OPEN");
		open.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topleft.add(open);
		open.setMaximumSize(new Dimension(140, 50));
		open.setAlignmentX(Component.CENTER_ALIGNMENT);
		topleft.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton close = new JButton ("CLOSE");
		close.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topleft.add(close);
		close.setAlignmentX(Component.CENTER_ALIGNMENT);
		close.setMaximumSize(new Dimension(140, 50));
		
		panel.add(topleft);
		
		JPanel topright = new JPanel();
		topright.setLayout(new BoxLayout(topright, BoxLayout.Y_AXIS));
		topright.setBounds(200, 0, 200, 160);
		topright.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		topright.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel flux = new JLabel("FLUX CAPACITOR");
		flux.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topright.add(flux);
		flux.setAlignmentX(Component.CENTER_ALIGNMENT);
		topright.add(Box.createRigidArea(new Dimension(0, 20)));
		JRadioButton clean = new JRadioButton("CLEAN");
		clean.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topright.add(clean);
		clean.setAlignmentX(Component.CENTER_ALIGNMENT);
		topright.add(Box.createRigidArea(new Dimension(0, 10)));
		JRadioButton vent = new JRadioButton("VENT");
		vent.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topright.add(vent);
		vent.setAlignmentX(Component.CENTER_ALIGNMENT);
		ButtonGroup bg = new ButtonGroup();
		bg.add(clean);
		bg.add(vent);
		
		panel.add(topright);
		
		JPanel bottomleft = new JPanel();
		bottomleft.setLayout(new BoxLayout(bottomleft, BoxLayout.Y_AXIS));
		bottomleft.setBounds(0, 200, 200, 200);
		bottomleft.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		bottomleft.add(Box.createRigidArea(new Dimension(0, 40)));
		JLabel nextron = new JLabel("NEXTRON");
		nextron.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomleft.add(nextron);
		nextron.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomleft.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton reset = new JButton("RESET");
		reset.setMaximumSize(new Dimension(120, 50));
		reset.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomleft.add(reset);
		reset.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(bottomleft);
		
		JPanel bottomright = new JPanel();
		bottomright.setLayout(new BoxLayout(bottomright, BoxLayout.Y_AXIS));
		bottomright.setBounds(200, 160, 200, 240);
		bottomright.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		bottomright.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel laser = new JLabel("LASER BEAM");
		laser.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomright.add(laser);
		laser.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomright.add(Box.createRigidArea(new Dimension(0, 45)));
		String[] array = new String[] {"0", "1", "2", "3"};
		JComboBox<String> levels = new JComboBox<String>(array);
		levels.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		levels.setMaximumSize(new Dimension(80, 50));
		bottomright.add(levels);
		
		panel.add(bottomright);
		
		widgets = new Vector<Widget>(4);
		widgets.add(new AnyButton("Space Gate", 2, 0, new Vector<String>(Arrays.asList("Open the Space Gate", "Close the Space Gate"))));
		widgets.add(new AnyButton("Nextron", 1, 0, new Vector<String>(Arrays.asList("Reset the Nextron"))));
		widgets.add(new AnyButton("Flux Capacitor", 2, 0, new Vector<String>(Arrays.asList("Clean the Flux Capacitor", "Vent the Flux Capacitor"))));
		widgets.add(new Slider("Laser Beam", 0, 4, 0));
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return widgets;
	}
}
