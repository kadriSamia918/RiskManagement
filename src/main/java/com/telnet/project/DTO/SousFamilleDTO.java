package com.telnet.project.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.telnet.project.Entities.AttributActif;

public class SousFamilleDTO {

private String id;
    
    @NotEmpty
    @Size(max = AttributActif.MAX_LENGTH_TITLE)
    private String nom;
    
    @Size(max = AttributActif.MAX_LENGTH_DESCRIPTION)
    private String description;

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

	public SousFamilleDTO(String id, @NotEmpty @Size(max = 100) String nom, @Size(max = 500) String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
	}

	public SousFamilleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
