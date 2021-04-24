package com.telnet.project.Entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection="Vulnerabilite")
public class Vulnerabilite {
	
	@Id
	private String id;
	
	private String code;
	
	private String description;

	private String famille;
	private String menace;
	
	@Field("actif")
    @JsonProperty(access = JsonProperty.Access.AUTO)
	@DBRef
    private List<Actif> actifList;
	
	
	
	public Vulnerabilite(String id, String code, String description, String famille, String menace,
			List<Actif> actifList) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.famille = famille;
		this.menace = menace;
		this.actifList = actifList;
	}

	public List<Actif> getActifList() {
		return actifList;
	}

	public void setActifList(List<Actif> actifList) {
		this.actifList = actifList;
	}

	public String getMenace() {
		return menace;
	}

	public void setMenace(String menace) {
		this.menace = menace;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Vulnerabilite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}





	
	

}
