package com.iavtar.app.model;

/**
 * @author indra_singh
 * */
public class Bundle {

	private int id;
	private double weight;
	private double cost;

	public Bundle(int id, double weight, double cost) {
		this.id = id;
		this.weight = weight;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
