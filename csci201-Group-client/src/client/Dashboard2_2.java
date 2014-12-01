package client;
import java.awt.*;
<<<<<<< HEAD
import java.util.Arrays;
=======
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
>>>>>>> branch 'master' of https://github.com/sstraw/csci201-Group
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Dashboard2_2 implements Dashboard
{
	private JPanel panel;
	private Vector<Widget> widgets;
	public Dashboard2_2(Client c)
	{
		panel = new JPanel();
		panel.setLayout(null);
		
		JPanel one = new JPanel();
		one.setLayout(new BoxLayout(one, BoxLayout.Y_AXIS));
		one.setBounds(0, 0, 180, 200);
		one.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		one.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel spectrum = new JLabel("HOLOSPECTRUM");
		one.add(spectrum);
		spectrum.setAlignmentX(Component.CENTER_ALIGNMENT);
		spectrum.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		one.add(Box.createRigidArea(new Dimension(0, 15)));
		JSlider setting = new JSlider(JSlider.VERTICAL, 0, 4, 2);
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
                	
                }
	        }
	    });
		
		panel.add(one);
		
		JPanel two = new JPanel();
		two.setLayout(new BoxLayout(two, BoxLayout.Y_AXIS));
		two.setBounds(180, 0, 320, 200);
		two.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		two.add(Box.createRigidArea(new Dimension(0, 25)));
		JLabel repulsion = new JLabel("REPULSION LOCATOR");
		repulsion.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		two.add(repulsion);
		repulsion.setAlignmentX(Component.CENTER_ALIGNMENT);
		two.add(Box.createRigidArea(new Dimension(0, 20)));
		JPanel sidetop = new JPanel();
		sidetop.setLayout(new BoxLayout(sidetop, BoxLayout.X_AXIS));
		JButton num0 = new JButton ("0");
		num0.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sidetop.add(num0);
		num0.setMaximumSize(new Dimension(100, 50));
		num0.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		sidetop.add(Box.createRigidArea(new Dimension(10, 0)));
		JButton num1 = new JButton ("1");
		num1.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sidetop.add(num1);
		num1.setMaximumSize(new Dimension(100, 50));
		num1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		two.add(sidetop);
		two.add(Box.createRigidArea(new Dimension(0, 10)));
		JPanel sidebottom = new JPanel();
		sidebottom.setLayout(new BoxLayout(sidebottom, BoxLayout.X_AXIS));
		JButton num2 = new JButton ("2");
		num2.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sidebottom.add(num2);
		num2.setMaximumSize(new Dimension(100, 50));
		num2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		sidebottom.add(Box.createRigidArea(new Dimension(10, 0)));
		JButton num3 = new JButton ("3");
		num3.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		sidebottom.add(num3);
		num3.setMaximumSize(new Dimension(100, 50));
		num3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		two.add(sidebottom);
		
		panel.add(two);
		
		JPanel three = new JPanel();
		three.setLayout(new BoxLayout(three, BoxLayout.Y_AXIS));
		three.setBounds(0, 200, 200, 230);
		three.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		three.add(Box.createRigidArea(new Dimension(0, 35)));
		JLabel whittler = new JLabel("EMERGENCY WHITTLER");
		three.add(whittler);
		whittler.setAlignmentX(Component.CENTER_ALIGNMENT);
		whittler.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
		three.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton baste = new JButton ("BASTE");
		baste.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		three.add(baste);
		baste.setMaximumSize(new Dimension(140, 50));
		baste.setAlignmentX(Component.CENTER_ALIGNMENT);
		baste.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		three.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton jiggle = new JButton ("JIGGLE");
		jiggle.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		three.add(jiggle);
		jiggle.setAlignmentX(Component.CENTER_ALIGNMENT);
		jiggle.setMaximumSize(new Dimension(140, 50));
		jiggle.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		
		panel.add(three);
		
		JPanel four = new JPanel();
		four.setLayout(new BoxLayout(four, BoxLayout.X_AXIS));
		four.setBounds(200, 200, 300, 115);
		four.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		four.add(Box.createRigidArea(new Dimension(30, 0)));
		JLabel accelerator = new JLabel("ACCELERATOR");
		four.add(accelerator);
		accelerator.setAlignmentY(Component.CENTER_ALIGNMENT);
		accelerator.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		four.add(Box.createRigidArea(new Dimension(20, 0)));
		JButton yes = new JButton ("");
		yes.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		four.add(yes);
		yes.setMaximumSize(new Dimension(100, 100));
		yes.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				
			}
		});
		
		panel.add(four);
		
		JPanel five = new JPanel();
		five.setLayout(new BoxLayout(five, BoxLayout.X_AXIS));
		five.setBounds(200, 315, 300, 115);
		five.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		five.add(Box.createRigidArea(new Dimension(40, 0)));
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
		five.add(levels);
		five.add(Box.createRigidArea(new Dimension(40, 0)));
		JLabel flush = new JLabel("FLUSHCLAMP");
		flush.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		five.add(flush);
		
		panel.add(five);
		
		widgets = new Vector<Widget>(5);
		widgets.add(new Slider("Holospectrum", 0, 5, 2));
		widgets.add(new Slider("Flushclamp", 0, 4, 0));
		widgets.add(new AnyButton("Emergency Whittler", 2, 0, new Vector<String>(Arrays.asList("Baste the Emergency Whittler", "Juggle the Emergency Whittler"))));
		widgets.add(new AnyButton("Repulsion Locator", 4, 0, new Vector<String>(Arrays.asList("Set Repulsion Locator to 0","Set Repulsion Locator to 1","Set Repulsion Locator to 2","Set Repulsion Locator to 3"))));
		widgets.add(new AnyButton("Accelerator", 1, 0, new Vector<String>(Arrays.asList("Start the Accelerator"))));
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return widgets;
	}
}
