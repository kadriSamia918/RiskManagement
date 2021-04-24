package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
@Document(collection="evenement")

public class Evenement {
	
	@Id
	private String id;
	private String code;
	private String date;
	private boolean status;
	
	private String type;
	private String description ;
	private String source;
	
	
	private String departement;
	 @DBRef
		@JsonProperty(access = JsonProperty.Access.AUTO)
	private CategorieIncident categorie;
	
	
	
	private String detecteur;
	
	
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getId() {
		return id;
	}
	
	
	
	
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public CategorieIncident getCategorie() {
		return categorie;
	}
	public void setCategorie(CategorieIncident categorie) {
		this.categorie = categorie;
	}
	public String getCode() {
		return code;
	}
	
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getDetecteur() {
		return detecteur;
	}
	public void setDetecteur(String detecteur) {
		this.detecteur = detecteur;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public Evenement(String id, String code, String date, boolean status, String description, String source,
			  String departement, CategorieIncident categorie, String type,User user,
			String detecteur) {
		super();
		this.id = id;
		this.code = code;
		this.date = date;
		this.type=type;
		
		this.status = status;
		this.description = description;
		this.source = source;
		this.departement = departement;
		this.categorie = categorie;
		this.detecteur = detecteur;
	}
	public Evenement() {
		super();
	}
	public boolean getStatus() {
		return status;
	}
	
	

}
