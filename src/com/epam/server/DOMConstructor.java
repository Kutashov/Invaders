package com.epam.server;

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


import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DOMConstructor {
	
	private Element planets;
	Document document = null;

	DOMConstructor (String key) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        document = documentBuilder.newDocument();
        Element rootElement = document.createElement("response");
        document.appendChild(rootElement);
        
        Element planets = document.createElement("planets");
        rootElement.appendChild(planets);
        
        this.planets = planets;
	}
	

	public StreamResult getResultXml (OutputStream out) throws TransformerException, FileNotFoundException {
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
       
        DOMSource domSource = new DOMSource(document);
        
        StreamResult streamResult = new StreamResult(out);
        transformer.transform(domSource, streamResult);
        
        return streamResult;
	}
	
	
	protected void addAction (Planet p) {
		
		Element planet = document.createElement("planet");
		Attr attr = document.createAttribute("id");
		attr.setValue(p.getId());
		planet.setAttributeNode(attr);
		planets.appendChild(planet);
		
		Element owner = document.createElement("owner");
		owner.appendChild(document.createTextNode(p.getOwner()));
		planet.appendChild(owner);
		
		Element type = document.createElement("type");
		type.appendChild(document.createTextNode(p.getType()));
		planet.appendChild(type);
		
		Element droids = document.createElement("droids");
		droids.appendChild(document.createTextNode(p.getDroids_num().toString()));
		planet.appendChild(droids);
		
		Element neighbours = document.createElement("neighbours");
		planet.appendChild(droids);
		
		for (String id : p.getNeighbours()) {
			Element neighbour = document.createElement("neighbour");
			neighbour.appendChild(document.createTextNode(id));
			neighbours.appendChild(neighbour);
		}
	}
	
	
}
