package ru.epam.invaderscup;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySAXParser extends DefaultHandler {
	
	static int mes_num = 0;
	private String thisElement;
	private Planet planet;
	private String attribute;

	@Override
	public void startDocument() throws SAXException {
		++mes_num;
		System.out.println("Got message #" + mes_num);
		System.out.println("Start parse XML...");
	}
	 
	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		thisElement = qName;
		if (thisElement.equals("planet")) {
			planet = new Planet();
			planet.setId(atts.getValue(0));
		}
		if (thisElement.equals("error")) {
			attribute = atts.getValue(0);
		}
	}
	 
	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if (qName.equals("planet")) {
			Client.galactic.updatePlanet(planet);
		}
		thisElement = "";
	}
	 
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (thisElement.equals("owner")) {
			planet.setOwner(new String(ch, start, length));
		} else if (thisElement.equals("type")) {
			planet.setType(new String(ch, start, length));
		} else if (thisElement.equals("droids")) {
			planet.setDroids_num(new Integer(new String(ch, start, length)));
		} else if (thisElement.equals("neighbour")) {
			planet.addNeighbour(new String(ch, start, length));
		} else if (thisElement.equals("error")) {
			
			System.out.println("Error: " + new String(ch, start, length));
			
			if (new String(ch, start, length).equals("User has not joined any game.")) {
				System.exit(-1);
			}
			
			if (attribute != null) {
			
				if (attribute.equals("gameover")) {
					System.exit(0);
				}
			}
		}
	 
	}
	 
	@Override
	public void endDocument() {
	  System.out.println("Stop parse XML...");
	}
	

}
