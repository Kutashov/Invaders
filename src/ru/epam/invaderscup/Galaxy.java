package ru.epam.invaderscup;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Galaxy {

	private static Map <String, Planet> planets = new Hashtable<String, Planet>();
	private String header = null;
	
	public void updatePlanet(Planet planet) {
		
		if (header == null && planet.getOwner().equals(Client.myName)) {
			header = planet.getId();
		}
			
		planets.put(planet.getId(), planet);
	}
	
	protected List<String> makeMyMove () {
		
		List<String> actions = new ArrayList<String>();
		
		for (Planet planet : planets.values()) {
		   if (planet.getOwner().equals(Client.myName)) {
			   if (planet.getDroids_num() > 499) {
				   String idmin = null;
				   Integer drmin = Integer.MAX_VALUE;
				   for (String id : planet.getNeighbours()) {
					   if (planets.get(id).getDroids_num()<drmin) {
						   idmin=id;
						   drmin=planets.get(id).getDroids_num();
					   }
				   }
				   actions.add(actionToString(planet.getId(), idmin, 140));
		     
			   }
		   }
		}
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
}
