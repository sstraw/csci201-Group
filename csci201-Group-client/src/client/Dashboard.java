package client;

import java.util.Vector;
import javax.swing.JPanel;

public interface Dashboard {

	public JPanel getPanel();
	public Vector<Widget> getWidgets();
}
