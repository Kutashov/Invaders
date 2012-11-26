package com.epam.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Server extends Thread {

	private static int port = 10040;
	private static long delay = 0;
	private static long period = 2000;
	private static final int players_num = 5;
	ServerSocket ss;
	
	public static void main(String[] args) throws IOException {
		
		Server server = new Server();
		
	             
	}   
	
	Server () throws IOException {
		
		ss = new ServerSocket(port);
	    System.out.println("Server Started");
	    
	    start();
	       
	}
	
	public void run() {
		
		Timer timer = new Timer();
	    
	    timer.scheduleAtFixedRate(new TimerTask () {

			@Override
			public void run() {
				Socket socket = null;
				try {
					socket = ss.accept();
					new ClientThread(socket);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				  
			}
	    	
	    }, delay, period);
	}
	   
}
