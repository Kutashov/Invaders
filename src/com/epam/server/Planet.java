package com.epam.server;


import java.util.ArrayList;
import java.util.List;

public class Planet {

	private String id;
	private String type;
	private String owner;
	private Integer droids_num;
	private List <String> neighbours = new ArrayList<String>();
	private Integer income;
	private Integer outcome;
	
	Planet () {
		owner = "nobody";
		income = 0;
		outcome = 0;
	}
	
	public String toString () {
		return "Planet #" + id + "(" + type + "); owner: " + owner +
				"; units = " + droids_num + "; neighbours: " + neighbours.toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Integer getDroids_num() {
		return droids_num;
	}
	public void setDroids_num(Integer droids_num) {
		this.droids_num = droids_num;
	}
	public List<String> getNeighbours() {
		return neighbours;
	}
	public void addNeighbour(String id) {

		if (!neighbours.contains(id)) {
			neighbours.add(id);
		}
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public Integer getOutcome() {
		return outcome;
	}

	public void setOutcome(Integer outcome) {
		this.outcome = outcome;
	}
	
	
	
}


