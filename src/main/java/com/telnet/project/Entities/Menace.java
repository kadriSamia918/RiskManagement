package com.telnet.project.Entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="Menace")
public class Menace {
	
	@Id
	private String id;
	
	private String code; 
	
	@DBRef
	@Field("type")
	private TypeMenace type;
	
	@DBRef
	@Field("vulnerabilite")
	private List<Vulnerabilite> vulnerabilite;
	
	private String description;
	
	@DBRef
	@Field("source")
	private SourceMenace source;
    
	private boolean delibere;
	
	private boolean accidentelle;
	
	private boolean environnementale;
	
	private boolean retenu;
	
	public boolean isDelibere() {
		return delibere;
	}

	public void setDelibere(boolean delibere) {
		this.delibere = delibere;
	}

	public boolean isAccidentelle() {
		return accidentelle;
	}

	public void setAccidentelle(boolean accidentelle) {
		this.accidentelle = accidentelle;
	}

	public boolean isEnvironnementale() {
		return environnementale;
	}

	public void setEnvironnementale(boolean environnementale) {
		this.environnementale = environnementale;
	}

	public boolean isRetenu() {
		return retenu;
	}

	public void setRetenu(boolean retenu) {
		this.retenu = retenu;
	}

	private String famille;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TypeMenace getType() {
		return type;
	}

	public void setType(TypeMenace type) {
		this.type = type;
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

	public SourceMenace getSource() {
		return source;
	}

	public void setSource(SourceMenace source) {
		this.source = source;
	}

	public Menace() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Vulnerabilite> getVulnerabilite() {
		return vulnerabilite;
	}

	public void setVulnerabilite(List<Vulnerabilite> vulnerabiliteList) {
		this.vulnerabilite= vulnerabiliteList;
	}




	public Menace(String id, String code, TypeMenace type, List<Vulnerabilite> vulnerabiliteList, String description,
			SourceMenace source, boolean delibere, boolean accidentelle, boolean environnementale, boolean retenu,
			String famille) {
		super();
		this.id = id;
		this.code = code;
		this.type = type;
		this.vulnerabilite = vulnerabiliteList;
		this.description = description;
		this.source = source;
		this.delibere = delibere;
		this.accidentelle = accidentelle;
		this.environnementale = environnementale;
		this.retenu = retenu;
		this.famille = famille;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}
	

}
