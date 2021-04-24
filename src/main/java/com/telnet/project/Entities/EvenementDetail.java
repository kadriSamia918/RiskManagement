package com.telnet.project.Entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "Incident")

public class EvenementDetail {
	@Id
	private String id;

	@DBRef
	@JsonProperty(access = JsonProperty.Access.AUTO)
	private List<Actif> actif;
    private String code;
    private String cause;
    private String cmntr;private String etat;
    private String source;
    private String date;
private String createdAt;
	
    private String detecteur;
    private String description;
    @DBRef

	 @JsonProperty(access = JsonProperty.Access.AUTO)
     private Action action;
	 private String criterImpact;
	 @DBRef
	 @JsonProperty(access = JsonProperty.Access.AUTO)
     private CategorieIncident categorie;
	 private String natureEvenement;
     @DBRef
     @JsonProperty(access = JsonProperty.Access.AUTO)
	 private Famille famille;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Actif> getActif() {
		return actif;
	}
	public void setActif(List<Actif> actif) {
		this.actif = actif;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getCmntr() {
		return cmntr;
	}
	public void setCmntr(String cmntr) {
		this.cmntr = cmntr;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getDetecteur() {
		return detecteur;
	}
	public void setDetecteur(String detecteur) {
		this.detecteur = detecteur;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public String getCriterImpact() {
		return criterImpact;
	}
	public void setCriterImpact(String criterImpact) {
		this.criterImpact = criterImpact;
	}
	public CategorieIncident getCategorie() {
		return categorie;
	}
	public void setCategorie(CategorieIncident categorie) {
		this.categorie = categorie;
	}
	public String getNatureEvenement() {
		return natureEvenement;
	}
	public void setNatureEvenement(String natureEvenement) {
		this.natureEvenement = natureEvenement;
	}
	public Famille getFamille() {
		return famille;
	}
	public void setFamille(Famille famille) {
		this.famille = famille;
	}
	public EvenementDetail(String id, List<Actif> actif, String code, String cause, String cmntr, String etat,
			String source, String date, String createdAt, String detecteur, String description, Action action,
			String criterImpact, CategorieIncident categorie, String natureEvenement, Famille famille) {
		super();
		this.id = id;
		this.actif = actif;
		this.code = code;
		this.cause = cause;
		this.cmntr = cmntr;
		this.etat = etat;
		this.source = source;
		this.date = date;
		this.createdAt = createdAt;
		this.detecteur = detecteur;
		this.description = description;
		this.action = action;
		this.criterImpact = criterImpact;
		this.categorie = categorie;
		this.natureEvenement = natureEvenement;
		this.famille = famille;
	}
	public EvenementDetail() {
		super();
	}
	
	
	
}
