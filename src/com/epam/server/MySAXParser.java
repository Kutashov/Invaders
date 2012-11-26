package com.epam.server;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySAXParser extends DefaultHandler {
	
	static int mes_num = 0;
	private String thisElement;
	private Planet planet;

	@Override
	public void startDocument() throws SAXException {
		
		
	}
	 
	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		thisElement = qName;
		if (thisElement.equals("request")) {
			planet = new Planet();
		}
	}
	 
	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		
		thisElement = "";
	}
	 
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (thisElement.equals("token")) {
			planet.setOwner(new String(ch, start, length));
		} else if (thisElement.equals("from")) {
			planet.setType(new String(ch, start, length));
		} else if (thisElement.equals("to")) {
			planet.setDroids_num(new Integer(new String(ch, start, length)));
		} else if (thisElement.equals("unitscount")) {
			planet.addNeighbour(new String(ch, start, length));
		}
	 
	}
	 
	@Override
	public void endDocument() {
	  System.out.println("Stop parse XML...");
	}
	

}
