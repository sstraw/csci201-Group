package client;
import java.awt.*;

import javax.swing.*;

public class Dashboard3_4 extends JPanel
{
	public Dashboard3_4()
	{
		setLayout(null);

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
		
		add(one);
		
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
		side.add(Box.createRigidArea(new Dimension(20, 0)));
		JButton engage = new JButton ("1");
		engage.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		side.add(engage);
		engage.setMaximumSize(new Dimension(100, 50));
		two.add(side);
		
		add(two);
		
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
		
		add(three);
		
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
		four.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton withdraw = new JButton ("WITHDRAW");
		withdraw.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		four.add(withdraw);
		withdraw.setAlignmentX(Component.CENTER_ALIGNMENT);
		withdraw.setMaximumSize(new Dimension(140, 50));
		four.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton deposit = new JButton ("DEPOSIT");
		deposit.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		four.add(deposit);
		deposit.setAlignmentX(Component.CENTER_ALIGNMENT);
		deposit.setMaximumSize(new Dimension(140, 50));
		
		add(four);
		
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
		
		add(five);
		
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
		
		add(six);
	}
}
