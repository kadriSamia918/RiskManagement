package com.telnet.project.Entities;

import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection="risque")
public class Risque {
	
	@Id
	private String id; 
	
	private String code; 
	
	@Field
	private String famille;
	
	@DBRef
	@JsonProperty(access = JsonProperty.Access.AUTO)
	private List<Actif> listActif;
	
	@DBRef
	@JsonProperty(access = JsonProperty.Access.AUTO)
	private Menace menace;
	
	private String scenarioRisque;
	
	private String consequence;
	
	private List<Vulnerabilite> vulnerabilite;
	

	@JsonProperty(access = JsonProperty.Access.AUTO)
	private String proprietaire;

    @DBRef
   	@JsonProperty(access = JsonProperty.Access.AUTO)
   	private SousFamille sousFamille;
    
    @DBRef
	@JsonProperty(access = JsonProperty.Access.AUTO)
    private RisqueDetail brut;
    
   @DBRef
	@JsonProperty(access = JsonProperty.Access.AUTO)
    private RisqueDetail reel;
    
    @DBRef
	@JsonProperty(access = JsonProperty.Access.AUTO)
	@Field
    private RisqueDetail residuel;
    
    private String mesureExistante;
    
    @DBRef

	@JsonProperty(access = JsonProperty.Access.AUTO)
    private Action action;
	@Field
    private String decisionTraitement;
    
    private String decisionAcceptation;
    
    private String date;
    
    private String saverId;
    
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSaverId() {
		return saverId;
	}

	public void setSaverId(String saverId) {
		this.saverId = saverId;
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

	public void setCode(String code_Risque) {
		this.code = code_Risque;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public List<Actif> getListActif() {
		return listActif;
	}

	public void setListActif(List<Actif> listActif) {
		this.listActif = listActif;
	}

	public Menace getMenace() {
		return menace;
	}

	public void setMenace(Menace menace) {
		this.menace = menace;
	}

	public String getScenarioRisque() {
		return scenarioRisque;
	}

	public void setScenarioRisque(String scenarioRisque) {
		this.scenarioRisque = scenarioRisque;
	}

	public String getConsequence() {
		return consequence;
	}

	public void setConsequence(String consequence) {
		this.consequence = consequence;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public RisqueDetail getBrut() {
		return brut;
	}

	public void setBrut(RisqueDetail brut) {
		this.brut = brut;
	}

	public RisqueDetail getReel() {
		return reel;
	}

	public void setReel(RisqueDetail reel) {
		this.reel = reel;
	}

	public RisqueDetail getResiduel() {
		return residuel;
	}

	public void setResiduel(RisqueDetail residuel) {
		this.residuel = residuel;
	}

	



	public List<Vulnerabilite> getVulnerabilite() {
		return vulnerabilite;
	}

	public void setVulnerabilite(List<Vulnerabilite> vulnerabilite) {
		this.vulnerabilite = vulnerabilite;
	}


	public String getMesureExistante() {
		return mesureExistante;
	}

	public void setMesureExistante(String mesureExistante) {
		this.mesureExistante = mesureExistante;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Risque() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDecisionTraitement() {
		return decisionTraitement;
	}

	public void setDecisionTraitement(String decisionTraitement) {
		this.decisionTraitement = decisionTraitement;
	}

	public String getDecisionAcceptation() {
		return decisionAcceptation;
	}

	public void setDecisionAcceptation(String decisionAcceptation) {
		this.decisionAcceptation = decisionAcceptation;
	}

	public SousFamille getSousFamille() {
		return sousFamille;
	}

	public void setSousFamille(SousFamille sousFamille) {
		this.sousFamille = sousFamille;
	}



	public Risque(String id, String code, String famille, List<Actif> listActif, Menace menace, String scenarioRisque,
			String consequence, List<Vulnerabilite> vulnerabilite, String proprietaire, SousFamille sousFamille,
			RisqueDetail brut, RisqueDetail reel, RisqueDetail residuel, String mesureExistante, Action action,
			String decisionTraitement, String decisionAcceptation, String date, String saverId) {
		super();
		this.id = id;
		this.code = code;
		this.famille = famille;
		this.listActif = listActif;
		this.menace = menace;
		this.scenarioRisque = scenarioRisque;
		this.consequence = consequence;
		this.vulnerabilite = vulnerabilite;
		this.proprietaire = proprietaire;
		this.sousFamille = sousFamille;
		this.brut = brut;
		this.reel = reel;
		this.residuel = residuel;
		this.mesureExistante = mesureExistante;
		this.action = action;
		this.decisionTraitement = decisionTraitement;
		this.decisionAcceptation = decisionAcceptation;
		this.date = date;
		this.saverId = saverId;
	}



	
	
	

}
