package client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;

public class Dashboard1_1 implements Dashboard
{
	private JPanel panel;
	private Vector<Widget> widgets;
	private Client client;
	public Dashboard1_1(Client c)
	{
		this.client = c;
		panel = new JPanel();
		panel.setLayout(null);
		
		JPanel topleft = new JPanel();
		topleft.setLayout(new BoxLayout(topleft, BoxLayout.Y_AXIS));
		topleft.setBounds(0, 0, 200, 200);
		topleft.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		topleft.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel hydra = new JLabel("HYDRAFLEX");
		hydra.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topleft.add(hydra);
		hydra.setAlignmentX(Component.CENTER_ALIGNMENT);
		topleft.add(Box.createRigidArea(new Dimension(0, 30)));
		String[] array = new String[] {"0", "1", "2", "3"};
		JComboBox<String> levels = new JComboBox<String>(array);
		levels.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		levels.setMaximumSize(new Dimension(80, 50));
		levels.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() instanceof JComboBox<?>){
					int i = ((JComboBox<?>) arg0.getSource()).getSelectedIndex();
					widgets.get(0).setVal(i);
					client.updateWidget(widgets.get(0));
				}
			}
		});
		topleft.add(levels);
		
		panel.add(topleft);
		
		JPanel topright = new JPanel();
		topright.setLayout(new BoxLayout(topright, BoxLayout.Y_AXIS));
		topright.setBounds(200, 0, 200, 160);
		topright.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		topright.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel transmission = new JLabel("TRANSMISSION");
		transmission.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topright.add(transmission);
		transmission.setAlignmentX(Component.CENTER_ALIGNMENT);
		topright.add(Box.createRigidArea(new Dimension(0, 20)));
		JRadioButton auto = new JRadioButton("AUTOMATIC");
		auto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				widgets.get(2).setVal(0);
				client.updateWidget(widgets.get(2));
			}
		});
		auto.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topright.add(auto);
		auto.setAlignmentX(Component.CENTER_ALIGNMENT);
		topright.add(Box.createRigidArea(new Dimension(0, 10)));
		JRadioButton manual = new JRadioButton("MANUAL");
		manual.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				widgets.get(2).setVal(1);
				client.updateWidget(widgets.get(2));
			}
		});
		manual.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		topright.add(manual);
		manual.setAlignmentX(Component.CENTER_ALIGNMENT);
		ButtonGroup bg = new ButtonGroup();
		auto.setSelected(true);
		bg.add(auto);
		bg.add(manual);
		
		panel.add(topright);
		
		JPanel bottomleft = new JPanel();
		bottomleft.setLayout(new BoxLayout(bottomleft, BoxLayout.Y_AXIS));
		bottomleft.setBounds(0, 200, 200, 200);
		bottomleft.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		bottomleft.add(Box.createRigidArea(new Dimension(0, 40)));
		JLabel cokecup = new JLabel("CROWLEY COKE CUP");
		cokecup.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomleft.add(cokecup);
		cokecup.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomleft.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton refill = new JButton("REFILL");
		refill.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				widgets.get(1).setVal(0);
				client.updateWidget(widgets.get(1));
			}
		});
		refill.setMaximumSize(new Dimension(120, 50));
		refill.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomleft.add(refill);
		refill.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(bottomleft);
		
		JPanel bottomright = new JPanel();
		bottomright.setLayout(new BoxLayout(bottomright, BoxLayout.Y_AXIS));
		bottomright.setBounds(200, 160, 200, 240);
		bottomright.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		bottomright.add(Box.createRigidArea(new Dimension(0, 30)));
		JLabel mosfet = new JLabel("MOSFET");
		bottomright.add(mosfet);
		mosfet.setAlignmentX(Component.CENTER_ALIGNMENT);
		mosfet.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		bottomright.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton ntype = new JButton ("N-CHANNEL");
		ntype.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				widgets.get(3).setVal(0);
				client.updateWidget(widgets.get(3));
			}
		});
		ntype.setFont(new Font("DejaVu Sans", Font.BOLD, 17));
		bottomright.add(ntype);
		ntype.setMaximumSize(new Dimension(160, 50));
		ntype.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomright.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton ptype = new JButton ("P-CHANNEL");
		ptype.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				widgets.get(3).setVal(1);
				client.updateWidget(widgets.get(3));
			}
		});
		ptype.setFont(new Font("DejaVu Sans", Font.BOLD, 17));
		bottomright.add(ptype);
		ptype.setAlignmentX(Component.CENTER_ALIGNMENT);
		ptype.setMaximumSize(new Dimension(160, 50));
		
		panel.add(bottomright);
		
		widgets = new Vector<Widget>(4);
		widgets.add(new Slider("Hydraflex", 0, 4, 0));
		widgets.add(new AnyButton("Crowley's Coke Cup", 1, 0, new Vector<String>(Arrays.asList("Refill Crowley's Coke Cup"))));
		widgets.add(new AnyButtonStored("Transmission", 2, 0, new Vector<String>(Arrays.asList("Switch Transmission to Automatic", "Switch Transmission to Manual"))));
		widgets.add(new AnyButton("MOSFTET", 2, 0, new Vector<String>(Arrays.asList("Add a new N-Channel MOSFET", "Add a new P-Channel Mosfet"))));
		
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public Vector<Widget> getWidgets() {
		return widgets;
	}

}
