package com.isga.model;

import com.isga.entities.Commande;

public class CommandModel {
	private Commande com;
	private double total;
	public CommandModel() {
		super();
	}
	public Commande getCom() {
		return com;
	}
	public void setCom(Commande com) {
		this.com = com;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}