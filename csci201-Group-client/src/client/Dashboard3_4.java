package client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Dashboard3_4 implements Dashboard
{
	private JPanel panel;
	private Vector<Widget> widgets;
	public Dashboard3_4(Client c)
	{
		panel = new JPanel();
		panel.setLayout(null);

		JPanel one = new JPanel();
		one.setLayout(new BoxLayout(one, BoxLayout.Y_AXIS));
		one.setBounds(0, 0, 180, 220);
		one.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		one.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel plectro = new JLabel("PLECTROBLADE");
		one.add(plectro);
		plectro.setAlignmentX(Component.CENTER_ALIGNMENT);
		plectro.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		one.add(Box.createRigidArea(new Dimension(0, 15)));
		JSlider setting = new JSlider(JSlider.VERTICAL, 0, 3, 3);
		setting.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
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
		JLabel bilge = new JLabel("BILGE AIRENGINES");
		bilge.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		two.add(bilge);
		bilge.setAlignmentX(Component.CENTER_ALIGNMENT);
		two.add(Box.createRigidArea(new Dimension(0, 20)));
		JPanel side = new JPanel();
		side.setLayout(new BoxLayout(side, BoxLayout.X_AXIS));
		JButton disengage = new JButton ("0");
		disengage.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
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
		engage.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		side.add(engage);
		engage.setMaximumSize(new Dimension(100, 50));
		engage.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 1;
				AnyButton currentwidget = (AnyButton)widgets.get(1);
				currentwidget.setVal(newval);
			}
		});
		two.add(side);
		
		panel.add(two);
		
		JPanel three = new JPanel();
		three.setLayout(new BoxLayout(three, BoxLayout.Y_AXIS));
		three.setBounds(0, 220, 180, 210);
		three.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		three.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel maneuvers = new JLabel("EVASIVE MANEUVERS");
		maneuvers.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		three.add(maneuvers);
		maneuvers.setAlignmentX(Component.CENTER_ALIGNMENT);
		three.add(Box.createRigidArea(new Dimension(0, 25)));
		JButton take = new JButton("TAKE");
		take.setMaximumSize(new Dimension(120, 100));
		take.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		three.add(take);
		take.setAlignmentX(Component.CENTER_ALIGNMENT);
		take.addActionListener(new ActionListener() 
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
		four.setBounds(180, 160, 160, 270);
		four.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		four.add(Box.createRigidArea(new Dimension(0, 25)));
		JLabel bank = new JLabel("BANK");
		four.add(bank);
		bank.setAlignmentX(Component.CENTER_ALIGNMENT);
		bank.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		four.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton balance = new JButton ("BALANCE");
		balance.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		four.add(balance);
		balance.setMaximumSize(new Dimension(140, 50));
		balance.setAlignmentX(Component.CENTER_ALIGNMENT);
		balance.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 0;
				AnyButton currentwidget = (AnyButton)widgets.get(3);
				currentwidget.setVal(newval);
			}
		});
		four.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton withdraw = new JButton ("WITHDRAW");
		withdraw.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		four.add(withdraw);
		withdraw.setAlignmentX(Component.CENTER_ALIGNMENT);
		withdraw.setMaximumSize(new Dimension(140, 50));
		withdraw.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 1;
				AnyButton currentwidget = (AnyButton)widgets.get(3);
				currentwidget.setVal(newval);
			}
		});
		four.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton deposit = new JButton ("DEPOSIT");
		deposit.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		four.add(deposit);
		deposit.setAlignmentX(Component.CENTER_ALIGNMENT);
		deposit.setMaximumSize(new Dimension(140, 50));
		deposit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 2;
				AnyButton currentwidget = (AnyButton)widgets.get(3);
				currentwidget.setVal(newval);
			}
		});
		
		panel.add(four);
		
		JPanel five = new JPanel();
		five.setLayout(new BoxLayout(five, BoxLayout.Y_AXIS));
		five.setBounds(340, 160, 160, 135);
		five.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		five.add(Box.createRigidArea(new Dimension(0, 10)));
		JLabel recycle = new JLabel("RECYCLING");
		recycle.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		five.add(recycle);
		recycle.setAlignmentX(Component.CENTER_ALIGNMENT);
		five.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton empty = new JButton("EMPTY");
		empty.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		five.add(empty);
		empty.setAlignmentX(Component.CENTER_ALIGNMENT);
		empty.setMaximumSize(new Dimension(100, 80));
		empty.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 0;
				AnyButton currentwidget = (AnyButton)widgets.get(4);
				currentwidget.setVal(newval);
			}
		});
		
		panel.add(five);
		
		JPanel six = new JPanel();
		six.setLayout(new BoxLayout(six, BoxLayout.Y_AXIS));
		six.setBounds(340, 295, 160, 135);
		six.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		six.add(Box.createRigidArea(new Dimension(0, 10)));
		JLabel hextrack = new JLabel("HEXTRACK");
		hextrack.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		six.add(hextrack);
		hextrack.setAlignmentX(Component.CENTER_ALIGNMENT);
		six.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton on = new JButton ("ON");
		on.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		six.add(on);
		on.setAlignmentX(Component.CENTER_ALIGNMENT);
		on.setMaximumSize(new Dimension(100, 80));
		on.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				int newval = 0;
				AnyButton currentwidget = (AnyButton)widgets.get(5);
				currentwidget.setVal(newval);
			}
		});
		
		panel.add(six);
		widgets = new Vector<Widget>(6);
		widgets.add(new Slider("Plectroblade", 0, 4, 0));
		widgets.add(new AnyButton("Bilge Airengines", 2, 0, new Vector<String>(Arrays.asList("Set Bilge Airengines to 0", "Set Bilge Airengines to 1"))));
		widgets.add(new AnyButton("Evasive Manuevers", 1, 0, new Vector<String>(Arrays.asList("Take Evasive Manuevers"))));
		widgets.add(new AnyButton("Bank", 3, 0, new Vector<String>(Arrays.asList("Check Bank Balance", "Withdraw from Bank", "Deposit in Bank"))));
		widgets.add(new AnyButton("Recycling", 1, 0, new Vector<String>(Arrays.asList("Empty the Recycling"))));
		widgets.add(new AnyButton("Hextrack", 1, 0, new Vector<String>(Arrays.asList("Turn On Hextrack"))));
	}

	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return widgets;
	}
}
