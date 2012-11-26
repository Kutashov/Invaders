package com.epam.server;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Galaxy {

	private static Map <String, Planet> planets = new Hashtable<String, Planet>();
	private String header = null;
	private boolean start = true;
	
	public void updatePlanet(Planet planet) {
		
		
			
		planets.put(planet.getId(), planet);
	}
	
	protected List<String> makeMyMove () {
		
		List<String> actions = new ArrayList<String>();
		
		
		return actions;
	}
	
	private String actionToString (String from, String to, Integer units) {
		String temp = null;
		temp = from + " ";
		temp += to + " ";
		temp += units;
		return temp;
	}
	
	public void toConsole () {
		System.out.println("Header = " + header);
		for (Planet planet : planets.values()) {
			System.out.println(planet.toString());
		}
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	
	
}
