package com.epam.client;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * 
 * @author Alexandr Kutashov
 * @version 1.2
 * 
 */

public class XMLParser {

    
    private SAXParser parser;
    private Client client;
    
    XMLParser (Client client) throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(false);
        
        this.client = client;
		parser = factory.newSAXParser();   
		
    }
    
    public void parse (InputStream xmlData) throws SAXException, IOException {
    	parser.parse(xmlData, new MySAXParser(client));
    }
}