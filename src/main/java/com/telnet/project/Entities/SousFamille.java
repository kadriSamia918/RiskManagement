package com.telnet.project.Entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;



@Document(collection = "SousFamille")
public class SousFamille {

	  public static final int MAX_LENGTH_DESCRIPTION = 500;
	  public static final int MAX_LENGTH_TITLE = 100;
	@Id
	private String id;

	private String nom;
   
	private String description;
     
    @DBRef
    @Field("actif")
	@JsonProperty(access = JsonProperty.Access.AUTO)
	private List<Actif> actifs;
    
public List<Actif> getActifs() {
		return actifs;
	}

	public void setActifs(List<Actif> actifs) {
		this.actifs = actifs;
	}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getNom() {
	return this.nom;
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

public SousFamille() {
	super();
	// TODO Auto-generated constructor stub
}

public SousFamille(String id, String nom, String description) {
	super();
	this.id = id;
	this.nom = nom;
	this.description = description;
}

private SousFamille(Builder builder) {
    this.description = builder.description;
    this.nom= builder.nom;
}

 
public static Builder getBuilder() {
    return new Builder();
}
public static class Builder {
 
private String description;

private String nom;

private Builder() {}

public Builder description(String description) {
    this.description = description;
    return this;
}
public Builder nom(String nom) {
    this.nom = nom;
    return this;
}
public SousFamille build() {
	SousFamille build = new SousFamille(this);
  //  build.checkTitleAndDescription(build.getNom(), build.getDescription());
    return build;
}

public Builder(String description, String title) {
	super();
	this.description = description;
	this.nom = title;
}
}
}
