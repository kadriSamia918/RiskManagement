package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="DegreCriticite")
public class DegreCriticite {
	
	@Id
	private String id; 
	
	private int degre;
	
	private String couleur;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDegre() {
		return degre;
	}

	public void setDegre(int degre) {
		this.degre = degre;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public DegreCriticite(String id, int degre, String couleur) {
		super();
		this.id = id;
		this.degre = degre;
		this.couleur = couleur;
	}

	public DegreCriticite() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
