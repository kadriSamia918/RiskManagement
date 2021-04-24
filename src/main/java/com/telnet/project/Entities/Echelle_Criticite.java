package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="Echelle_Criticite")
public class Echelle_Criticite {
	
	@Id
	private String id;
	
	private String nom;
	
	private String description;
	
	private String degre;
	
	private String couleur;
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Echelle_Criticite(String id, String nom, String description, String degre, String couleur) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.degre = degre;
		this.couleur = couleur;
	}
	public String getDegre() {
		return degre;
	}
	public void setDegre(String degre) {
		this.degre = degre;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public Echelle_Criticite() {
		super();
	}
	
	
	private Echelle_Criticite(Builder builder) {
        this.description = builder.description;
        this.nom= builder.nom;
        this.couleur=builder.couleur;
        this.degre=builder.degre;
    }
	
	 
  public static Builder getBuilder() {
        return new Builder();
    }
public static class Builder {
	 
    private String description;

    private String nom;

    private String degre;
    
    private String couleur;
    
    private Builder() {}

    public Builder description(String description) {
        this.description = description;
        return this;
    }

    public Builder nom(String nom) {
        this.nom = nom;
        return this;
    }
    public Builder couleur(String couleur) {
        this.couleur = couleur;
        return this;
    }
    public Builder degre(String degre) {
        this.degre = degre;
        return this;
    }


    public Echelle_Criticite build() {
    	Echelle_Criticite build = new Echelle_Criticite(this);

      //  build.checkTitleAndDescription(build.getNom(), build.getDescription());

        return build;
    }

	public Builder(String description, String nom,String couleur,String degre) {
		super();
		this.description = description;
		this.nom = nom;
		this.couleur = couleur;
		this.degre=degre;
	}
}


}
