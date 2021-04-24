package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CategoryAction")
public class CategorieAction {
	
	
	@Id
	
	private String id;
	private String categorie;
	
	
	public CategorieAction() {
		super();
	}
	public CategorieAction(String id, String categorie) {
		super();
		this.id = id;
		this.categorie = categorie;
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
