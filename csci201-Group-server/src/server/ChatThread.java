package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class ChatThread extends Thread {
	private static Semaphore semaphore = new Semaphore(2);
	private Socket s;
	private Server server;
	private PrintWriter pw;
	public ChatThread(Socket s, Server server) {
		this.s = s;
		this.server = server;
		
		try {
			this.pw = new PrintWriter(this.s.getOutputStream());
		} catch (IOException ioe) {
			System.out.println("ioe in ChatThread: " + ioe.getMessage());
		}
	}
	
	public void send(String message) {
		pw.println(message);
		pw.flush();
	}
	
	public void run() {
		// a client has connected to the server
		try {
			semaphore.acquire();
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			while(true) {
				String line;
				while((line = br.readLine()) != null){
					server.sendMessage(line, this);
					semaphore.release();
				}
			}
		} catch (IOException | InterruptedException ioe) {
			server.removeChatThread(this);
			System.out.println("Client disconnected from " + s.getInetAddress());
		}
	}
}