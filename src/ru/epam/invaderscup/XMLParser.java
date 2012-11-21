package ru.epam.invaderscup;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XMLParser {

    
    private SAXParser parser;
    
    XMLParser () throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(false);

		parser = factory.newSAXParser();     
    }
    
    public void parse (InputStream xmlData) throws SAXException, IOException {
    	parser.parse(xmlData, new MySAXParser());
    }
}