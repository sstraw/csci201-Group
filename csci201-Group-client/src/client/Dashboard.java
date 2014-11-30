package client;

import java.util.Vector;
import javax.swing.JPanel;

public interface Dashboard {
	public JPanel getPanel();
	public Vector<Widget> getWidgets();
}

class DashboardFactory{
	
	public static final Dashboard getDashboard(Client c, int level, int index){
		switch(level){
		case(1):
			switch(index){
			case(1):
				return new Dashboard1_1(c);
			case(2):
				return new Dashboard1_2(c);
			case(3):
				return new Dashboard1_3(c);
			case(4):
				return new Dashboard1_4(c);
			}
		case(2):
			switch(index){
			case(1):
				return new Dashboard2_1(c);
			case(2):
				return new Dashboard2_2(c);
			case(3):
				return new Dashboard2_3(c);
			case(4):
				return new Dashboard2_4(c);
			}
		case(3):
			switch(index){
			case(1):
				return new Dashboard3_1(c);
			case(2):
				return new Dashboard3_2(c);
			case(3):
				return new Dashboard3_3(c);
			case(4):
				return new Dashboard3_4(c);
			}
		case(4):
			switch(index){
			case(1):
				return new Dashboard4_1(c);
			case(2):
				return new Dashboard4_2(c);
			case(3):
				return new Dashboard4_3(c);
			case(4):
				return new Dashboard4_4(c);
			}
		case(5):
			switch(index){
			case(1):
				return new Dashboard5_1(c);
			case(2):
				return new Dashboard5_2(c);
			case(3):
				return new Dashboard5_3(c);
			case(4):
				return new Dashboard5_4(c);
			}
		}
		return null;
	}
}