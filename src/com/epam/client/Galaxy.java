package com.epam.client;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Alexandr Kutashov
 * @version 1.2
 * 
 */

public class Galaxy {

	private static Map <String, Planet> planets = new Hashtable<String, Planet>();
	private String header = null;
	private boolean start = true;
	Client client;
	
	public Galaxy(Client client) {
		this.client = client;
	}

	public void updatePlanet(Planet planet) {
		
		if (header == null && planet.getOwner().equals(client.getMyName())) {
			header = planet.getId();
		}
			
		planets.put(planet.getId(), planet);
	}
	
	protected List<String> makeMyMove () {
		
		List<String> actions = new ArrayList<String>();
		
		if (!start) {
			return actions;
		}
		
		List<String> visit_list = new ArrayList<String>();
		
		
		for (Planet planet : planets.values()) {
			if (planet.getNeighbours().size() == 1 && planet.getOwner().equals(client.getMyName())) {
				visit_list.add(planet.getId());
			}
		}
		
		boolean wasneighbour = true;
		int begin = 0;
		int end = visit_list.size();
		
		while (wasneighbour) {
			wasneighbour = false;
			for (int i  = begin; i < end; ++i) {
				
				++begin;
				Planet planet = planets.get(visit_list.get(i));
				for (String id : planet.getNeighbours()) {
					if (planets.get(id).getOwner().equals(client.getMyName()) && !visit_list.contains(id)) {
						visit_list.add(id);
						wasneighbour = true;
					}
				}
			}
			end = visit_list.size();
		}
		
		for (Planet planet : planets.values()) {
			if (planet.getOwner().equals(client.getMyName()) && !visit_list.contains(planet.getId())) {
				visit_list.add(planet.getId());
			}
		}
		
		boolean transfer;
		
		for (String key : visit_list) {
			Planet curplanet = planets.get(key);
			
			transfer = false;
			
			
			for (String id : curplanet.getNeighbours()) {
				if (planets.get(id).getOwner().equals("nobody")) {
						
						int diff = curplanet.getMyPotential();
						if (diff > 0) {
							actions.add(actionToString(key, id, diff));
							transfer = true;
							break;
						} else {
							if (curplanet.expectedInAMove() > curplanet.getTransferMinimum()) {
								actions.add(actionToString(key, id, 1));
							}
						}
				}
			}
			
			if (!transfer) {
				int diff = curplanet.getMyPotential();
				if (diff > 0) {
					List<String> min = new ArrayList<String>();
					for (String id : curplanet.getNeighbours()) {
						
						if (planets.get(id).getDroids_num() < curplanet.getDroids_num() &&
								planets.get(id).getOwner().equals(client.getMyName()) &&
								planets.get(id).expectedDroidsNum() < planets.get(id).getTransferMinimum()) {
							min.add(id);
						}
					}
					if (min.size() == 0) {
						for (String id : curplanet.getNeighbours()) {
							
							if (planets.get(id).getOwner().equals(client.getMyName()) &&
									planets.get(id).expectedDroidsNum() < planets.get(id).getTransferMinimum()) {
								min.add(id);
							}
						}
					}
					if (min.size() == 1) {
						if (curplanet.getType().compareTo(planets.get(min.get(0)).getType()) < 0) {
							int res = Math.min(curplanet.getRemainedDroids() - curplanet.getMinimum(), 
									planets.get(min.get(0)).getTransferMinimum() - planets.get(min.get(0)).expectedDroidsNum());
							actions.add(actionToString(key, min.get(0), res));
							transfer = true;
						} else {
							actions.add(actionToString(key, min.get(0), diff));
						   	transfer = true;
						}
					} else {
						if (min.size() != 0) {
							diff /= min.size();
							for (String id : min) {
							   	actions.add(actionToString(key, id, diff));
							   	transfer = true;
							}
						}
					}
					
					   	
				}
					 
			}
			
			
			if (!transfer) {
				for (String id : curplanet.getNeighbours()) {
				
					if (!(planets.get(id).getOwner().equals(client.getMyName()) || planets.get(id).getOwner().equals("nobody"))) {
						int diff = curplanet.getRemainedDroids() - planets.get(id).getEnemyPotential();
						if (diff >= 0 && curplanet.getMyPotential() > 0) {
							actions.add(actionToString(key, id, curplanet.getRemainedDroids() - curplanet.getMinimum()));
							break;
						} else if (isAlone(planets.get(id))) {
							int pot = curplanet.getMyPotential();
							if (pot > 0) {
								actions.add(actionToString(key, id, pot));
							}	
						}
					}
				}	
			}
			
			if (curplanet.getMyPotential() > 0) {
				int max = 0;
				for (String id : curplanet.getNeighbours()) {
					if (planets.get(id).getNeighbours().size() > max &&
							planets.get(id).getOwner().equals(client.getMyName())) {
						max = planets.get(id).getNeighbours().size();
					}
				}
				List<String> maxlist = new ArrayList<String>();
				for (String id : curplanet.getNeighbours()) {
					if (planets.get(id).getNeighbours().size() == max &&
							planets.get(id).getOwner().equals(client.getMyName())) {
						maxlist.add(id);
					}
				}
				if (maxlist.size() != 0) {
					int diff = curplanet.getMyPotential()/maxlist.size();
					for (String id : maxlist) {
						actions.add(actionToString(key, id, diff));
					}
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
		planets.get(from).addOutcome(units);
		planets.get(to).addIncome(units);
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

	protected boolean isAlone (Planet planet) {
		
		int val = 0;
		for (String id : planet.getNeighbours()) {
			if (planets.get(id).getOwner().equals(planet.getOwner())) {
				++val;
			}
		}
		if (val == 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
