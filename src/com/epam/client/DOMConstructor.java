package com.epam.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * 
 * @author Alexandr Kutashov
 * @version 1.2
 * 
 */

public class DOMConstructor {
	
	private Element actions;
	Document document = null;

	DOMConstructor (String key) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        document = documentBuilder.newDocument();
        Element rootElement = document.createElement("request");
        document.appendChild(rootElement);
        
        
        Element token = document.createElement("token");
        token.appendChild(document.createTextNode(key));
        rootElement.appendChild(token);

        
        Element actions = document.createElement("actions");
        rootElement.appendChild(actions);
        
        this.actions = actions;
	}
	

	public StreamResult getResultXml (OutputStream out) throws TransformerException, FileNotFoundException {
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
       
        DOMSource domSource = new DOMSource(document);
        
        StreamResult streamResult = new StreamResult(out);
        transformer.transform(domSource, streamResult);
        
        return streamResult;
	}
	
	
	protected void addAction (String fromId, String toId, String droids_num) {
		
		Element action = document.createElement("action");
		actions.appendChild(action);
		
		Element from = document.createElement("from");
		from.appendChild(document.createTextNode(fromId));
		action.appendChild(from);
		
		Element to = document.createElement("to");
		to.appendChild(document.createTextNode(toId));
		action.appendChild(to);
		
		Element units = document.createElement("unitscount");
		units.appendChild(document.createTextNode(droids_num));
		action.appendChild(units);
	}
	
	
}
