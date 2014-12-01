package client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Dashboard2_1 implements Dashboard
{
	private JPanel panel;
	private Vector<Widget> widgets;
	public Dashboard2_1(Client c)
	{
		panel = new JPanel();
		panel.setLayout(null);
		
		JPanel one = new JPanel();
		one.setLayout(new BoxLayout(one, BoxLayout.Y_AXIS));
		one.setBounds(0, 0, 180, 220);
		one.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		one.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel plexus = new JLabel("FINITE PLEXUS");
		one.add(plexus);
		plexus.setAlignmentX(Component.CENTER_ALIGNMENT);
		plexus.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		one.add(Box.createRigidArea(new Dimension(0, 15)));
		JSlider setting = new JSlider(JSlider.VERTICAL, 0, 3, 3);
		setting.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		setting.setMajorTickSpacing(1);
		setting.setPaintTicks(true);
		setting.setPaintLabels(true);
		one.add(setting);
		setting.setAlignmentX(Component.CENTER_ALIGNMENT);
		setting.addChangeListener(new ChangeListener() 
		{
	        public void stateChanged(ChangeEvent ce) 
	        {
	        	JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting())
                {
                	int newval = source.getValue();
                	Slider currentwidget = (Slider)widgets.get(0);
                	currentwidget.setVal(newval);
                }
	        }
	    });
		
		panel.add(one);
		
		JPanel two = new JPanel();
		two.setLayout(new BoxLayout(two, BoxLayout.Y_AXIS));
		two.setBounds(180, 0, 320, 160);
		two.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		two.add(Box.createRigidArea(new Dimension(0, 35)));
		JLabel turbo = new JLabel("TURBO JUMPER");
		turbo.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		two.add(turbo);
		turbo.setAlignmentX(Component.CENTER_ALIGNMENT);
		two.add(Box.createRigidArea(new Dimension(0, 20)));
		JPanel side = new JPanel();
		side.setLayout(new BoxLayout(side, BoxLayout.X_AXIS));
		JButton disengage = new JButton ("0");
		disengage.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		side.add(disengage);
		disengage.setMaximumSize(new Dimension(100, 50));
		disengage.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 0;
				AnyButton currentwidget = (AnyButton)widgets.get(1);
				currentwidget.setVal(newval);
			}
		});
		side.add(Box.createRigidArea(new Dimension(20, 0)));
		JButton engage = new JButton ("1");
		engage.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		side.add(engage);
		engage.setMaximumSize(new Dimension(100, 50));
		engage.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 0;
				AnyButton currentwidget = (AnyButton)widgets.get(1);
				currentwidget.setVal(newval);
			}
		});
		two.add(side);
		
		panel.add(two);
		
		JPanel three = new JPanel();
		three.setLayout(new BoxLayout(three, BoxLayout.Y_AXIS));
		three.setBounds(0, 220, 180, 230);
		three.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		three.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel leftovers = new JLabel("LEFTOVERS");
		leftovers.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		three.add(leftovers);
		leftovers.setAlignmentX(Component.CENTER_ALIGNMENT);
		three.add(Box.createRigidArea(new Dimension(0, 25)));
		JButton freeze = new JButton("FREEZE");
		freeze.setMaximumSize(new Dimension(120, 100));
		freeze.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		three.add(freeze);
		freeze.setAlignmentX(Component.CENTER_ALIGNMENT);
		freeze.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 0;
				AnyButton currentwidget = (AnyButton)widgets.get(2);
				currentwidget.setVal(newval);
			}
		});
		
		panel.add(three);
		
		JPanel four = new JPanel();
		four.setLayout(new BoxLayout(four, BoxLayout.Y_AXIS));
		four.setBounds(180, 160, 160, 290);
		four.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		four.add(Box.createRigidArea(new Dimension(0, 25)));
		JLabel tissue = new JLabel("IONTISSUE");
		four.add(tissue);
		tissue.setAlignmentX(Component.CENTER_ALIGNMENT);
		tissue.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		four.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton engorge = new JButton ("ENGORGE");
		engorge.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		four.add(engorge);
		engorge.setMaximumSize(new Dimension(140, 50));
		engorge.setAlignmentX(Component.CENTER_ALIGNMENT);
		engorge.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 0;
				AnyButton currentwidget = (AnyButton)widgets.get(3);
				currentwidget.setVal(newval);
			}
		});
		four.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton wipe = new JButton ("WIPE");
		wipe.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		four.add(wipe);
		wipe.setAlignmentX(Component.CENTER_ALIGNMENT);
		wipe.setMaximumSize(new Dimension(140, 50));
		wipe.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 0;
				AnyButton currentwidget = (AnyButton)widgets.get(3);
				currentwidget.setVal(newval);
			}
		});
		four.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton uncork = new JButton ("UNCORK");
		uncork.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		four.add(uncork);
		uncork.setAlignmentX(Component.CENTER_ALIGNMENT);
		uncork.setMaximumSize(new Dimension(140, 50));
		uncork.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 0;
				AnyButton currentwidget = (AnyButton)widgets.get(3);
				currentwidget.setVal(newval);
			}
		});
		
		panel.add(four);
		
		JPanel five = new JPanel();
		five.setLayout(new BoxLayout(five, BoxLayout.Y_AXIS));
		five.setBounds(340, 160, 160, 290);
		five.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		five.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel moon = new JLabel("MOONBEAM");
		moon.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		five.add(moon);
		moon.setAlignmentX(Component.CENTER_ALIGNMENT);
		five.add(Box.createRigidArea(new Dimension(0, 65)));
		String[] array = new String[] {"0", "1", "2", "3"};
		JComboBox<String> levels = new JComboBox<String>(array);
		levels.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		levels.setMaximumSize(new Dimension(80, 50));
		levels.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				JComboBox<String> source = (JComboBox<String>)ae.getSource();
				int newval = 0;
				Slider currentwidget = (Slider)widgets.get(4);
				
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
			}
		});
		five.add(levels);
		
		panel.add(five);

		widgets = new Vector<Widget>(5);
		widgets.add(new Slider("Finite Plexus", 0, 4, 3));
		widgets.add(new AnyButton("Turbo Jumper", 2, 0, new Vector<String>(Arrays.asList("Set Turbo Jumper to 0","Set Turbo Jumper to 1"))));
		widgets.add(new AnyButton("Leftovers", 1, 0, new Vector<String>(Arrays.asList("Freeze the Leftovers"))));
		widgets.add(new AnyButton("Iontissue", 3, 0, new Vector<String>(Arrays.asList("Engorge the Iontissue","Wipe the Iontissue","Uncork the Iontissue"))));
		widgets.add(new Slider("Moonbeam", 0, 4, 0));	
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return widgets;
	}
}
