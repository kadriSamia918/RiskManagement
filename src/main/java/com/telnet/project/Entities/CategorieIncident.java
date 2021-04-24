package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CategoryIncident")
public class CategorieIncident {
@Id
	
	private String id;
	private String categorie;
	private String description;
	
	
	
	
	
	public CategorieIncident() {
		super();
	}

	
	public CategorieIncident(String id, String categorie, String description) {
		super();
		this.id = id;
		this.categorie = categorie;
		this.description = description;
	}


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	
	
	
}
