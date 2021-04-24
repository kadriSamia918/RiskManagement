package com.telnet.project.Entities;

import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;


@Document(collection = "Category")
public class Category {
	
	
	public static final int MAX_LENGTH_DESCRIPTION = 500;
    public static final int MAX_LENGTH_TITLE = 100;
	@Id
	private String id;

	private String nom;
 
	private String description;
	/*@DBRef
	 @JsonProperty(access = JsonProperty.Access.AUTO)
	private Set<Famille> familles;
*/
	public String getId() {
		return id;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public Category(String id, String nom, String description, Set<Famille> familles) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		//this.familles = familles;
	}
	/*public Set<Famille> getFamilles() {
		return familles;
	}
	public void setFamilles(Set<Famille> familles) {
		this.familles = familles;
	}*/
	public String getNom() {
		return nom;
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

	public void setId(String id) {
		this.id = id;
	}
	
	
	private Category(Builder builder) {
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

    public Category build() {
    	Category build = new Category(this);

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
