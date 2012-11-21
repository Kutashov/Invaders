package ru.epam.invaderscup;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Client {

	protected static final String myName = "Sashok69";
	private String token = "a52fvccmarwyu2ol2occ22vilqmbkmml";
	private String address = "62.16.90.89";
	private int port = 10040;
	private Socket conn;
	protected static Galaxy galactic = new Galaxy();;
	private static Client client = new Client();
	private BufferedOutputStream out;
	private BufferedReader in;
	
	
	Client () {
		
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws TransformerException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws TransformerException, ParserConfigurationException, SAXException, IOException, InterruptedException {
		

		Client client = Client.getInstance();
		XMLData xml = new XMLData(client.getToken());
		
		
		while (true) {
			
			client.sendData(xml);
			xml.setData(client.getData());
			xml.parseData();
			xml.applyChanges(galactic.makeMyMove());
		}
		

	}
	
	private static Client getInstance() {
		
		return client;
	}

	public void sendData (XMLData xml) throws UnknownHostException, IOException, TransformerException, ParserConfigurationException, SAXException, InterruptedException {

		conn = new Socket(address, port);
		out = new BufferedOutputStream(conn.getOutputStream());
		
		xml.sendData(out, false);
		
		
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	


	

}
