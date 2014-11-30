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
				return new Dashboard1_1();
			case(2):
				return new Dashboard1_2();
			case(3):
				return new Dashboard1_3();
			case(4):
				return new Dashboard1_4();
			}
		case(2):
			switch(index){
			case(1):
				return new Dashboard2_1();
			case(2):
				return new Dashboard2_2();
			case(3):
				return new Dashboard2_3();
			case(4):
				return new Dashboard2_4();
			}
		case(3):
			switch(index){
			case(1):
				return new Dashboard3_1();
			case(2):
				return new Dashboard3_2();
			case(3):
				return new Dashboard3_3();
			case(4):
				return new Dashboard3_4();
			}
		case(4):
			switch(index){
			case(1):
				return new Dashboard4_1();
			case(2):
				return new Dashboard4_2();
			case(3):
				return new Dashboard4_3();
			case(4):
				return new Dashboard4_4();
			}
		case(5):
			switch(index){
			case(1):
				return new Dashboard5_1();
			case(2):
				return new Dashboard5_2();
			case(3):
				return new Dashboard5_3();
			case(4):
				return new Dashboard5_4();
			}
		}
	}
}