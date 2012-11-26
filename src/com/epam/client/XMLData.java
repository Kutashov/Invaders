package com.epam.client;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


import org.xml.sax.SAXException;

/**
 * 
 * @author Alexandr Kutashov
 * @version 1.2
 * 
 */

public class XMLData {

	private String data;
	private XMLParser xmlparser;
	private DOMConstructor constructor;
	private Client client;
	
	public XMLData(Client client) throws ParserConfigurationException, SAXException, IOException {
		xmlparser = new XMLParser(client);
		constructor = new DOMConstructor(client.getToken());
		this.client = client;
	}

	public void parseData () throws FileNotFoundException, SAXException, IOException {
		
		xmlparser.parse(new ByteArrayInputStream(data.getBytes()));
	}

	public void sendData(OutputStream out, boolean rewrite) throws TransformerException, ParserConfigurationException, SAXException, IOException {

		constructor.getResultXml(out);
		
		if (rewrite) {
			constructor = new DOMConstructor(client.getToken());
		}	
	}
	
	public void setData (String data) {

		this.data = data;
	}
	
	public void addAction (String from, String to, String droids_num) {
		
		constructor.addAction(from, to, droids_num);
	}
	
	public String toString () {
		return data;
	}
	

	public void applyChanges(List<String> actions) {

		
		for (String act : actions) {
			String [] acts = act.split(" ");
			addAction(acts[0], acts[1], acts[2]);
		}
	}
	
}
