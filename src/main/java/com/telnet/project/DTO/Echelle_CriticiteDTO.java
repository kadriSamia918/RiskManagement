package com.telnet.project.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import com.telnet.project.Entities.AttributActif;

public class Echelle_CriticiteDTO {
	
	@Id
    private String id;
    
    @NotEmpty
    @Size(max = AttributActif.MAX_LENGTH_TITLE)
    private String nom;
    
    @Size(max = AttributActif.MAX_LENGTH_DESCRIPTION)
    private String description;

    private String couleur;
    
    private String degre;
    
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



	public Echelle_CriticiteDTO(String id, @NotEmpty @Size(max = 100) String nom, @Size(max = 500) String description,
			String couleur, String degre) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.couleur = couleur;
		this.degre = degre;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getDegre() {
		return degre;
	}

	public void setDegre(String degre) {
		this.degre = degre;
	}

	public Echelle_CriticiteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

    

}
