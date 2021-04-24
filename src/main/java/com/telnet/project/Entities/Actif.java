package com.telnet.project.Entities;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "Actif")
public class Actif {
	
	
	@Id
	private String id;
	
    private HashMap<String,String > data;
    @DBRef
    @JsonProperty(access = JsonProperty.Access.AUTO)
    private Echelle_Criticite confidentialite;
    @DBRef
    @JsonProperty(access = JsonProperty.Access.AUTO)
    private Echelle_Criticite integrite;
    @DBRef
    @JsonProperty(access = JsonProperty.Access.AUTO)
    private  Echelle_Criticite disponibilite;
    @DBRef
    @JsonProperty(access = JsonProperty.Access.AUTO)
    private Personne proprietaire;
    
    private Date savingDate;
    
    @Field("vulnerabilite")
   
    @JsonProperty(access = JsonProperty.Access.AUTO)
    private List<Vulnerabilite> vulnerabiliteList;
    
    private String sousFamille;
	public String getSousFamille()
	{
		return sousFamille;
	}

	public List<Vulnerabilite> getVulnerabiliteList() {
		return vulnerabiliteList;
	}

	public void setVulnerabiliteList(List<Vulnerabilite> vulnerabiliteList) {
		this.vulnerabiliteList = vulnerabiliteList;
	}

	public void setSousFamille(String sousFamille) {
		this.sousFamille = sousFamille;
	}

	public String getId() {
		return id;
	}

	public Actif() {
		super();
		// TODO Auto-generated constructor stub
	}


	



	public Actif(String id, HashMap<String, String> data, Echelle_Criticite confidentialite,
			Echelle_Criticite integrite, Echelle_Criticite disponibilite, Personne proprietaire,
			List<Vulnerabilite> vulnerabiliteList, String sousFamille) {
		super();
		this.id = id;
		this.data = data;
		this.confidentialite = confidentialite;
		this.integrite = integrite;
		this.disponibilite = disponibilite;
		this.proprietaire = proprietaire;
		this.vulnerabiliteList = vulnerabiliteList;
		this.sousFamille = sousFamille;
	}

	public Echelle_Criticite getConfidentialite() {
		return confidentialite;
	}

	public void setConfidentialite(Echelle_Criticite confidentialite) {
		this.confidentialite = confidentialite;
	}

	public Echelle_Criticite getIntegrite() {
		return integrite;
	}

	public void setIntegrite(Echelle_Criticite integrite) {
		this.integrite = integrite;
	}

	public Echelle_Criticite getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(Echelle_Criticite disponibilite) {
		this.disponibilite = disponibilite;
	}

	public Personne getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Personne proprietaire) {
		this.proprietaire = proprietaire;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashMap<String, String> getData() {
		return data;
	}

	public void setData(HashMap<String, String> data) {
		this.data = data;
	}

	public Date getSavingDate() {
		return savingDate;
	}

	public void setSavingDate(Date savingDate) {
		this.savingDate = savingDate;
	}

	public Actif(String id, HashMap<String, String> data, Echelle_Criticite confidentialite,
			Echelle_Criticite integrite, Echelle_Criticite disponibilite, Personne proprietaire, Date savingDate,
			List<Vulnerabilite> vulnerabiliteList, String sousFamille) {
		super();
		this.id = id;
		this.data = data;
		this.confidentialite = confidentialite;
		this.integrite = integrite;
		this.disponibilite = disponibilite;
		this.proprietaire = proprietaire;
		this.savingDate = savingDate;
		this.vulnerabiliteList = vulnerabiliteList;
		this.sousFamille = sousFamille;
	}
    
    
	

}
