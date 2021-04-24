package com.telnet.project.Entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "Critere_Securite")
public class Critere_Securite {
	
	@Id
	private String id;
	
	private String nom;
	
	@DBRef
	@JsonProperty(access = JsonProperty.Access.AUTO)
	@Field("niveau")
	private List<Echelle_Criticite> niveau;
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Critere_Securite(String id, String nom, List<Echelle_Criticite> niveau) {
		super();
		this.id = id;
		this.nom = nom;
		this.niveau = niveau;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		
		this.nom = nom;
		
	}

	public List<Echelle_Criticite> getNiveau() {
		return niveau;
	}

	public void setNiveau(List<Echelle_Criticite> niveau) {
		this.niveau = niveau;
	}

	public Critere_Securite() {
		super();
	}
	
	
	
	

}
