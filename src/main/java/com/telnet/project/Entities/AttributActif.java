package com.telnet.project.Entities;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "AttributActif")
public class AttributActif implements Serializable {
	  public static final int MAX_LENGTH_DESCRIPTION = 500;
	   public static final int MAX_LENGTH_TITLE = 100;
	@Id
	private String id;

    private String nom;
    
    private String type;
    
    private Boolean reference;
    
    private Boolean description;
    
    private Boolean list;
    
    private Set<String> choices;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AttributActif() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AttributActif(String id, String nom, String type, Boolean reference, Boolean description, Boolean list,
			Set<String> choices) {
		super();
		this.id = id;
		this.nom = nom;
		this.type = type;
		this.reference = reference;
		this.description = description;
		this.list = list;
		this.choices = choices;
	}

	public Boolean getReference() {
		return reference;
	}

	public void setReference(Boolean reference) {
		this.reference = reference;
	}

	public Boolean getDescription() {
		return description;
	}

	public void setDescription(Boolean description) {
		this.description = description;
	}

	public Boolean getList() {
		return list;
	}

	public void setList(Boolean list) {
		this.list = list;
	}

	public Set<String> getChoices() {
		return choices;
	}

	public void setChoices(Set<String> choices) {
		this.choices = choices;
	}

	private AttributActif(Builder builder) {
        this.type = builder.type;
        this.nom= builder.nom;
    }
	
	 
  public static Builder getBuilder() {
        return new Builder();
    }
public static class Builder {
	 
    private String type;

    private String nom;

    private Builder() {}

    public Builder type(String type) {
        this.type = type;
        return this;
    }

    public Builder nom(String nom) {
        this.nom = nom;
        return this;
    }

    public AttributActif build() {
    	AttributActif build = new AttributActif(this);

      //  build.checkTitleAndDescription(build.getNom(), build.getDescription());

        return build;
    }

	public Builder(String type, String nom) {
		super();
		this.type = type;
		this.nom = nom;
	}
}


}
