package client;
import java.awt.*;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Dashboard2_4 implements Dashboard
{
	private JPanel panel;
	private Vector<Widget> widgets;
	private Client client;
	public Dashboard2_4(Client c)
	{
		this.client = c;
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
				int newval = 0;
				AnyButton currentwidget = (AnyButton)widgets.get(0);
				currentwidget.setVal(newval);
				client.updateWidget(widgets.get(0));
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
				int newval = 0;
				AnyButtonStored currentwidget = (AnyButtonStored)widgets.get(1);
				currentwidget.setVal(newval);
				client.updateWidget(widgets.get(1));
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
				int newval = 1;
				AnyButtonStored currentwidget = (AnyButtonStored)widgets.get(1);
				currentwidget.setVal(newval);
				client.updateWidget(widgets.get(1));
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
				int newval = 2;
				AnyButtonStored currentwidget = (AnyButtonStored)widgets.get(1);
				currentwidget.setVal(newval);
				client.updateWidget(widgets.get(1));
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
				int newval = 3;
				AnyButtonStored currentwidget = (AnyButtonStored)widgets.get(1);
				currentwidget.setVal(newval);
				client.updateWidget(widgets.get(1));
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
                	int newval = source.getValue();
                	Slider currentwidget = (Slider)widgets.get(2);
                	currentwidget.setVal(newval);
                	client.updateWidget(widgets.get(2));
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
				JComboBox<?> source = (JComboBox<?>)ae.getSource();
				int newval = 0;
				Slider currentwidget = (Slider)widgets.get(3);
				
				String newselection = (String)source.getSelectedItem();
				if(newselection.equals("0"))
				{
					newval = 0;
				}
				else if(newselection.equals("1"))
				{
					newval = 1;
				}
				else if(newselection.equals("2"))
				{
					newval = 2;
				}
				else if(newselection.equals("3"))
				{
					newval = 3;
				}
				currentwidget.setVal(newval);
				client.updateWidget(widgets.get(3));
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
				int newval = 0;
				AnyButton currentwidget = (AnyButton)widgets.get(4);
				currentwidget.setVal(newval);
				client.updateWidget(widgets.get(4));
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
				int newval = 1;
				AnyButton currentwidget = (AnyButton)widgets.get(4);
				currentwidget.setVal(newval);
				client.updateWidget(widgets.get(4));
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
				int newval = 2;
				AnyButton currentwidget = (AnyButton)widgets.get(4);
				currentwidget.setVal(newval);
				client.updateWidget(widgets.get(4));
			}
		});
		five.add(side);
		
		panel.add(five);
		
		widgets = new Vector<Widget>(5);
		widgets.add(new AnyButton("Foreign Dignitaries", 1, 0, new Vector<String>(Arrays.asList("Entertain Foreign Dignitaries"))));
		widgets.add(new AnyButtonStored("Holowheel", 4, 0, new Vector<String>(Arrays.asList("Cast the Holowheel", "Reel the Holowheel", "Roll the Holowheel", "Flip the Holowheel"))));
		widgets.add(new Slider("Radiation", 0, 5, 2));
		widgets.add(new Slider("RadioCortex", 0, 4, 0));
		widgets.add(new AnyButton("Progyro", 3, 0, new Vector<String>(Arrays.asList("Set Progyro to 0", "Set Progyro to 1", "Set Progyro to 2"))));
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return widgets;
	}
}
