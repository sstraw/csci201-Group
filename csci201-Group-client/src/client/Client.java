package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Thread {
	ArrayList<Dashboard> levelOneDashboards; //will hold hardcoded set of Dashboards for each level
	ArrayList<Dashboard> levelTwoDashboards;
	ArrayList<Dashboard> levelThreeDashboards;
	ArrayList<Dashboard> levelFourDashboards;
	ArrayList<Dashboard> levelFiveDashboards;
	int currentLevel;
	Dashboard currentDashboard;
	
	public Client(String hostname, int port, Scanner scan) {
		//establish connection with server
				
	}
	
	void setLevel(int level) {
		
	}
	
	void chooseDashboard(int index) {  //
		if (currentLevel==1) {
			currentDashboard = levelOneDashboards.get(index);
		} else if (currentLevel==2) {
			currentDashboard = levelTwoDashboards.get(index);
		} else if (currentLevel==3) {
			currentDashboard = levelThreeDashboards.get(index);
		} else if (currentLevel==4) {
			currentDashboard = levelFourDashboards.get(index);
		} else if (currentLevel==5) {
			currentDashboard = levelFiveDashboards.get(index);
		}
		
	}
	
	
}

