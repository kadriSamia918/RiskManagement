package com.telnet.project.Entities;

import java.io.Serializable;
import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;



@Document(collection = "Famille")
public class Famille implements Serializable {

	  public static final int MAX_LENGTH_DESCRIPTION = 500;
	   public static final int MAX_LENGTH_TITLE = 100;
	@Id
	private String id;

   private String nom;
   
   private String description;
	  @DBRef
	  @JsonProperty(access = JsonProperty.Access.AUTO)
	  @Field("category")
	  private Category category;
	  @DBRef
	  @JsonProperty(access = JsonProperty.Access.AUTO)
	  @Field("attributActif")
	  private List<AttributActif> attributs;
	 
	  @DBRef
	  @JsonProperty(access = JsonProperty.Access.AUTO)
	  @Field("actif")
   	 private List<Actif> actifs;
	 @DBRef
	  @JsonProperty(access = JsonProperty.Access.AUTO)
	 @Field("sousFamille")
	 private List<SousFamille> sousFamilles; 
	
	 @Field("vulnerabilite")
	    @DBRef
	    private List<Vulnerabilite> vulnerabiliteList;

	 @Field("menace")
	    @DBRef
	    private List<Menace> menaceList;



	public Famille(String id, String nom, String description, Category category, List<AttributActif> attributs,
			List<Actif> actifs, List<SousFamille> sousFamille, List<Vulnerabilite> vulnerabiliteList,
			List<Menace> menaceList, Personne propriétaire) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.category = category;
		this.attributs = attributs;
		this.actifs = actifs;
		this.sousFamilles = sousFamille;
		this.vulnerabiliteList = vulnerabiliteList;
		this.menaceList = menaceList;
		this.propriétaire = propriétaire;
	}

	public List<Menace> getMenaceList() {
		return menaceList;
	}

	public void setMenaceList(List<Menace> menaceList) {
		this.menaceList = menaceList;
	}

	public List<SousFamille> getSousFamille() {
		return sousFamilles;
	}

	public void setSousFamille(List<SousFamille> sousFamille) {
		this.sousFamilles = sousFamille;
	}

	public List<Actif> getActifs() {
		return actifs;
	}

	public Personne getPropriétaire() {
		return propriétaire;
	}

	public void setPropriétaire(Personne propriétaire) {
		this.propriétaire = propriétaire;
	}

	public void setActifs(List<Actif> actifs) {
		this.actifs = actifs;
	}

 @DBRef
	  @JsonProperty(access = JsonProperty.Access.AUTO)
	  private Personne propriétaire;
	  
public List<AttributActif> getAttributs() {
		return attributs;
	}

	public void setAttributs(List<AttributActif> attributs) {
		this.attributs = attributs;
	}

public String getId() {
	return id;

}

public void setId(String id) {
	this.id = id;
}

public String getNom() {
	
	return nom;
	
}

public Category getCategory() {
	return category;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public static int getMaxLengthDescription() {
	return MAX_LENGTH_DESCRIPTION;
}

public static int getMaxLengthTitle() {
	return MAX_LENGTH_TITLE;
}
public void setCategory(Category category) {
	this.category = category;
}


public List<Vulnerabilite> getVulnerabiliteList() {
	return vulnerabiliteList;
}

public void setVulnerabiliteList(List<Vulnerabilite> vulnerabiliteList) {
	this.vulnerabiliteList = vulnerabiliteList;
}

public Famille() {
	super();
	// TODO Auto-generated constructor stub
}


private Famille(Builder builder) {
    this.description = builder.description;
    this.nom= builder.nom;
    this.attributs=builder.attributs;
    this.category=builder.category;
    this.actifs=builder.actifs;
}

 
public static Builder getBuilder() {
    return new Builder();
}
public static class Builder {
 
private String description;

private String nom;

private Category category;
private List<AttributActif> attributs;
private List<Actif> actifs;
private Personne proprietaire;
public List<Actif> getActifs() {
	return actifs;
}

public void setActifs(List<Actif> actifs) {
	this.actifs = actifs;
}

public Personne getProprietaire() {
	return proprietaire;
}

public void setProprietaire(Personne proprietaire) {
	this.proprietaire = proprietaire;
}

private Builder() {}

public Builder description(String description) {
    this.description = description;
    return this;
}

public Builder category(Category category) {
    this.category = category;
    return this;
}
public Builder attributs(List<AttributActif> attributs) {
    this.attributs=attributs;
    return this;
}
public Builder actifs(List<Actif> actifs) {
    this.actifs=actifs;
    return this;
}
public Builder nom(String nom) {
    this.nom = nom;
    return this;
}

public Famille build() {
	Famille build = new Famille(this);

  //  build.checkTitleAndDescription(build.getNom(), build.getDescription());

    return build;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public Category getCategory() {
	return category;
}



public List<AttributActif> getAttributs() {
	return attributs;
}

public void setAttributs(List<AttributActif> attributs) {
	this.attributs = attributs;
}

public void setCategory(Category category) {
	this.category = category;
}

public Builder(String description, String nom, Category category, List<AttributActif> attributs, List<Actif> actifs,
		Personne proprietaire) {
	super();
	this.description = description;
	this.nom = nom;
	this.category = category;
	this.attributs = attributs;
	this.actifs = actifs;
	this.proprietaire = proprietaire;
}
}
}
