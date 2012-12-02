package com.epam.client;


import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Alexandr Kutashov
 * @version 1.2
 * 
 */

public class Planet {

	private String id;
	private String type;
	private String owner;
	private Integer droids_num;
	private List <String> neighbours = new ArrayList<String>();
	private Integer income;
	private Integer outcome;
	
	//--------------------------constants-------------------------------
	private final double REGEN_TYPE_A = 1.1;
	private final double REGEN_TYPE_B = 1.15;
	private final double REGEN_TYPE_C = 1.2;
	private final double REGEN_TYPE_D = 1.3;
	private final int MIN_TRANSFER_TYPE_A = 91;
	private final int MIN_TRANSFER_TYPE_B = 174;
	private final int MIN_TRANSFER_TYPE_C = 417;
	private final int MIN_TRANSFER_TYPE_D = 770;
	private final int MIN_TYPE_A = 10;
	private final int MIN_TYPE_B = 7;
	private final int MIN_TYPE_C = 5;
	private final int MIN_TYPE_D = 4;
	private final int MAX_TYPE_A = 100;
	private final int MAX_TYPE_B = 200;
	private final int MAX_TYPE_C = 500;
	private final int MAX_TYPE_D = 1000;
	//--------------------------constants-------------------------------
	
	
	Planet () {
		owner = "nobody";
		income = 0;
		outcome = 0;
	}
	
	public String toString () {
		return "Planet #" + id + "(" + type + "); owner: " + owner +
				"; units = " + droids_num + "; neighbours: " + neighbours.toString();
	}
	
	protected String getId() {
		return id;
	}
	protected void setId(String id) {
		this.id = id;
	}
	protected String getType() {
		return type;
	}
	protected void setType(String type) {
		this.type = type;
	}
	protected String getOwner() {
		return owner;
	}
	protected void setOwner(String owner) {
		this.owner = owner;
	}
	protected Integer getDroids_num() {
		return droids_num;
	}
	protected void setDroids_num(Integer droids_num) {
		this.droids_num = droids_num;
	}
	protected List<String> getNeighbours() {
		return neighbours;
	}
	protected void addNeighbour(String id) {

		if (!neighbours.contains(id)) {
			neighbours.add(id);
		}
	}

	protected Integer getIncome() {
		return income;
	}

	protected void addIncome(Integer income) {
		this.income += income;
	}

	protected Integer getOutcome() {
		return outcome;
	}

	protected void addOutcome(Integer outcome) {
		this.outcome += outcome;
	}
	
	protected Integer getRemainedDroids () {
		return droids_num - outcome;
	}
	
	protected Integer getEnemyPotential () {
		
		int res = droids_num;
		if (type.equals("TYPE_A")) {
			res *= REGEN_TYPE_A;
			if (res > MAX_TYPE_A) {
				res = MAX_TYPE_A;
			}
		} else if (type.equals("TYPE_B")) {
			res *= REGEN_TYPE_B;
			if (res > MAX_TYPE_B) {
				res = MAX_TYPE_B;
			}
		} else if (type.equals("TYPE_C")) {
			res *= REGEN_TYPE_C;
			if (res > MAX_TYPE_C) {
				res = MAX_TYPE_C;
			}
		} else {
			res *= REGEN_TYPE_D;
			if (res > MAX_TYPE_D) {
				res = MAX_TYPE_D;
			}
		}
		return res;
	} 
	
	protected Integer getMinimum () {
		
		if (type.equals("TYPE_A")) {
			return MIN_TYPE_A;
		} else if (type.equals("TYPE_B")) {
			return MIN_TYPE_B;
		} else if (type.equals("TYPE_C")) {
			return MIN_TYPE_C;
		} else {
			return MIN_TYPE_D;
		}
	}
	
	protected Integer getTransferMinimum () {
		
		if (type.equals("TYPE_A")) {
			return MIN_TRANSFER_TYPE_A;
		} else if (type.equals("TYPE_B")) {
			return MIN_TRANSFER_TYPE_B;
		} else if (type.equals("TYPE_C")) {
			return MIN_TRANSFER_TYPE_C;
		} else {
			return MIN_TRANSFER_TYPE_D;
		}
	}
	
	protected Integer getMyPotential () {
		return Math.min(droids_num + income - outcome - getTransferMinimum(), droids_num - outcome);
	}

	protected Integer expectedDroidsNum() {
		return droids_num + income - outcome;
	}
	
	protected Integer expectedInAMove () {
		
			int res = droids_num + income - outcome;
			if (type.equals("TYPE_A")) {
				res *= REGEN_TYPE_A;
				if (res > MAX_TYPE_A) {
					res = MAX_TYPE_A;
				}
			} else if (type.equals("TYPE_B")) {
				res *= REGEN_TYPE_B;
				if (res > MAX_TYPE_B) {
					res = MAX_TYPE_B;
				}
			} else if (type.equals("TYPE_C")) {
				res *= REGEN_TYPE_C;
				if (res > MAX_TYPE_C) {
					res = MAX_TYPE_C;
				}
			} else {
				res *= REGEN_TYPE_D;
				if (res > MAX_TYPE_D) {
					res = MAX_TYPE_D;
				}
			}
			return res;
		
	}
	
}


