package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Privilege")
public class Privilege {
	
	@Id
	String id;
	
	String name;
	
	boolean lire;
	
	boolean ajouter;
	boolean supprimer;
	
	boolean modifier;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public boolean isLire() {
		return lire;
	}

	public void setLire(boolean lire) {
		this.lire = lire;
	}

	public boolean isAjouter() {
		return ajouter;
	}

	public void setAjouter(boolean ajouter) {
		this.ajouter = ajouter;
	}

	public boolean isSupprimer() {
		return supprimer;
	}

	public void setSupprimer(boolean supprimer) {
		this.supprimer = supprimer;
	}

	public boolean isModifier() {
		return modifier;
	}

	public void setModifier(boolean modifier) {
		this.modifier = modifier;
	}

	public Privilege() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Privilege(String id, String name, boolean lire, boolean ajouter, boolean supprimer, boolean modifier) {
		super();
		this.id = id;
		this.name = name;
		this.lire = lire;
		this.ajouter = ajouter;
		this.supprimer = supprimer;
		this.modifier = modifier;
	}
	
	

}
