package com.epam.server;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class ClientThread extends Thread {

	
	private Socket conn;
	private BufferedOutputStream out;
	private BufferedReader in;
	
	
	ClientThread (Socket socket) throws IOException {
		conn = socket;
		start();
	}
	
	public void run() {
	       try {
	         
	    	   
	       } finally {
	          try {
	             conn.close();
	          }
	          catch (IOException e) {
	             System.err.println("Socket not closed");
	          }
	       }
	}
	

	public void sendData (XMLData xml) throws UnknownHostException, IOException, TransformerException, ParserConfigurationException, SAXException, InterruptedException {
		
		out = new BufferedOutputStream(conn.getOutputStream());
		xml.sendData(out, true);
		
	}
	
	public String getData () throws UnknownHostException, IOException {
		
		in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder msg = new StringBuilder();
		
		String str;
		while ((str = in.readLine()) != null) {
			msg.append(str);
		}
		
		return msg.toString();
	}

}
