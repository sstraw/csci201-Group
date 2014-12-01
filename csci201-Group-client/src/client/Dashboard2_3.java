package client;
import java.awt.*;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Dashboard2_3 implements Dashboard
{
	private JPanel panel;
	private Vector<Widget> widgets;
	public Dashboard2_3(Client c)
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
		levels.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				JComboBox<String> source = (JComboBox<String>)ae.getSource();
				int newval = 0;
				AnyButton currentwidget = (AnyButton)widgets.get(0);
				
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
		run.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 0;
				AnyButtonStored currentwidget = (AnyButtonStored)widgets.get(1);
				currentwidget.setVal(newval);
			}
		});
		two.add(Box.createRigidArea(new Dimension(0, 10)));
		JRadioButton analysis = new JRadioButton("ANALYZE");
		analysis.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		two.add(analysis);
		analysis.setAlignmentX(Component.CENTER_ALIGNMENT);
		analysis.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 1;
				AnyButtonStored currentwidget = (AnyButtonStored)widgets.get(1);
				currentwidget.setVal(newval);
			}
		});
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
                }
	        }
	    });
		
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
		setting.addChangeListener(new ChangeListener() 
		{
	        public void stateChanged(ChangeEvent ce) 
	        {
	        	JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting())
                {
                	int newval = source.getValue();
                	Slider currentwidget = (Slider)widgets.get(3);
                	currentwidget.setVal(newval);
                }
	        }
	    });
		
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
		yes.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 0;
				AnyButton currentwidget = (AnyButton)widgets.get(4);
				currentwidget.setVal(newval);
			}
		});
		five.add(Box.createRigidArea(new Dimension(30, 0)));
		JLabel infratoxin = new JLabel("INFRATOXIN");
		infratoxin.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		five.add(infratoxin);
		
		panel.add(five);
		
		widgets = new Vector<Widget>(5);
		widgets.add(new Slider("Sloping Solution", 0, 4, 0));
		widgets.add(new AnyButtonStored("Monodish", 2, 0, new Vector<String>(Arrays.asList("Set Monodish to Run", "Set Monodish to Analyze"))));
		widgets.add(new Slider("Induction Iron", 0, 4, 0));
		widgets.add(new Slider("Flange Exhaust", 0, 6, 0));
		widgets.add(new AnyButton("Infratoxin", 1, 0, new Vector<String>(Arrays.asList("Spitball the Infratoxin"))));
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return widgets;
	}
}
