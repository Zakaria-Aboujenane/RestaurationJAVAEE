package com.isga.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.isga.entities.Categorie;
import com.isga.entities.Menu;
public class Model implements Serializable {
	
	private List<Menu> listeMenu;
	private List<Categorie> listeCat ;
	private Model modelToShow;


	
	

	public List<Categorie> getListeCat() {
		return listeCat;
	}


	public void setListeCat(List<Categorie> listeCat) {
		this.listeCat = listeCat;
	}


	public Model() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Model(List<Menu> listeMenu) {
		super();
		this.listeMenu = listeMenu;
		this.listeCat = new ArrayList<>();
	}


	public List<Menu> getListeMenu() {
		return listeMenu;
	}

	public void setListeMenu(List<Menu> listeMenu) {
		this.listeMenu = listeMenu;
	}


	public Model getModelToShow() {
		return modelToShow;
	}


	public void setModelToShow(Model modelToShow) {
		this.modelToShow = modelToShow;
	}
	
	
	
	
}
