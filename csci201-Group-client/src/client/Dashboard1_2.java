package client;


import java.awt.*;
import javax.swing.*;

public class Dashboard1_2 extends JPanel
{
	public Dashboard1_2()
	{
		setLayout(null);
		JPanel topleft = new JPanel();
		topleft.setLayout(new BoxLayout(topleft, BoxLayout.Y_AXIS));
		topleft.setBounds(0, 0, 200, 200);
		topleft.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		topleft.setLayout(new BoxLayout(topleft, BoxLayout.PAGE_AXIS));
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
		
		add(topleft);
	}

}
