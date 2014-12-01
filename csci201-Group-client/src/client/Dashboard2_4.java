package client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Dashboard2_4 implements Dashboard
{
	private JPanel panel;
	public Dashboard2_4(Client c)
	{
		panel = new JPanel();
		panel.setLayout(null);
		
		JPanel one = new JPanel();
		one.setLayout(new BoxLayout(one, BoxLayout.Y_AXIS));
		one.setBounds(0, 0, 200, 200);
		one.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		one.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel dignitaries = new JLabel("FOREIGN DIGNITARIES");
		dignitaries.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
		one.add(dignitaries);
		dignitaries.setAlignmentX(Component.CENTER_ALIGNMENT);
		one.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton entertain = new JButton("ENTERTAIN");
		entertain.setMaximumSize(new Dimension(120, 100));
		entertain.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		one.add(entertain);
		entertain.setAlignmentX(Component.CENTER_ALIGNMENT);
		entertain.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		
		panel.add(one);
		
		JPanel two = new JPanel();
		two.setLayout(new BoxLayout(two, BoxLayout.Y_AXIS));
		two.setBounds(200, 0, 150, 260);
		two.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		two.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel wheel = new JLabel("HOLOWHEEL");
		wheel.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		two.add(wheel);
		wheel.setAlignmentX(Component.CENTER_ALIGNMENT);
		two.add(Box.createRigidArea(new Dimension(0, 20)));
		JRadioButton cast = new JRadioButton("CAST");
		cast.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		two.add(cast);
		cast.setAlignmentX(Component.CENTER_ALIGNMENT);
		cast.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		two.add(Box.createRigidArea(new Dimension(0, 15)));
		JRadioButton reel = new JRadioButton("REEL");
		reel.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		two.add(reel);
		reel.setAlignmentX(Component.CENTER_ALIGNMENT);
		reel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		two.add(Box.createRigidArea(new Dimension(0, 15)));
		JRadioButton roll = new JRadioButton("ROLL");
		roll.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		two.add(roll);
		roll.setAlignmentX(Component.CENTER_ALIGNMENT);
		roll.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		two.add(Box.createRigidArea(new Dimension(0, 15)));
		JRadioButton flip = new JRadioButton("FLIP");
		flip.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		two.add(flip);
		flip.setAlignmentX(Component.CENTER_ALIGNMENT);
		flip.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		ButtonGroup bg = new ButtonGroup();
		bg.add(cast);
		bg.add(reel);
		bg.add(roll);
		bg.add(flip);
		
		panel.add(two);
		
		JPanel three = new JPanel();
		three.setLayout(new BoxLayout(three, BoxLayout.Y_AXIS));
		three.setBounds(350, 0, 150, 260);
		three.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		three.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel radiation = new JLabel("RADIATION");
		three.add(radiation);
		radiation.setAlignmentX(Component.CENTER_ALIGNMENT);
		radiation.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		three.add(Box.createRigidArea(new Dimension(0, 15)));
		JSlider settings = new JSlider(JSlider.VERTICAL, 0, 4, 2);
		settings.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		settings.setMajorTickSpacing(1);
		settings.setPaintTicks(true);
		settings.setPaintLabels(true);
		three.add(settings);
		settings.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings.addChangeListener(new ChangeListener() 
		{
	        public void stateChanged(ChangeEvent ce) 
	        {
	        	JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting())
                {
                	
                }
	        }
	    });
		
		panel.add(three);
		
		JPanel four = new JPanel();
		four.setLayout(new BoxLayout(four, BoxLayout.Y_AXIS));
		four.setBounds(0, 200, 200, 230);
		four.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		four.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel radio = new JLabel("RADIOCORTEX");
		four.add(radio);
		radio.setAlignmentX(Component.CENTER_ALIGNMENT);
		radio.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		four.add(Box.createRigidArea(new Dimension(0, 45)));
		String[] array = new String[] {"0", "1", "2", "3"};
		JComboBox<String> levels = new JComboBox<String>(array);
		levels.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		levels.setMaximumSize(new Dimension(80, 50));
		levels.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		four.add(levels);
		
		panel.add(four);
		
		JPanel five = new JPanel();
		five.setLayout(new BoxLayout(five, BoxLayout.Y_AXIS));
		five.setBounds(200, 260, 300, 170);
		five.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		five.add(Box.createRigidArea(new Dimension(0, 35)));
		JLabel progyro = new JLabel("PROGYRO");
		progyro.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		five.add(progyro);
		progyro.setAlignmentX(Component.CENTER_ALIGNMENT);
		five.add(Box.createRigidArea(new Dimension(0, 20)));
		JPanel side = new JPanel();
		side.setLayout(new BoxLayout(side, BoxLayout.X_AXIS));
		JButton num0 = new JButton ("0");
		num0.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		side.add(num0);
		num0.setMaximumSize(new Dimension(100, 50));
		num0.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		side.add(Box.createRigidArea(new Dimension(10, 0)));
		JButton num1 = new JButton ("1");
		num1.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		side.add(num1);
		num1.setMaximumSize(new Dimension(100, 50));
		num1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		side.add(Box.createRigidArea(new Dimension(10, 0)));
		JButton num2 = new JButton ("2");
		num2.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		side.add(num2);
		num2.setMaximumSize(new Dimension(100, 50));
		num2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		five.add(side);
		
		panel.add(five);
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return null;
	}
}
