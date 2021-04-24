package com.telnet.project.DTO;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.AttributActif;
import com.telnet.project.Entities.Category;

public class FamilleDTO {

	private String id;
    
    @NotEmpty
    @Size(max = AttributActif.MAX_LENGTH_TITLE)
    private String Nom;
    
    @Size(max = AttributActif.MAX_LENGTH_DESCRIPTION)
    private String description;
    
    private Category category;
    
    private List<AttributActif> attributs;
    private List<Actif> actifs;
    
	public List<Actif> getActifs() {
		return actifs;
	}

	public void setActifs(List<Actif> actifs) {
		this.actifs = actifs;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

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
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FamilleDTO(String id, @NotEmpty @Size(max = 100) String nom, @Size(max = 500) String description, Category category,List<AttributActif> attributs,
			List<Actif> actifs) {
		super();
		this.id = id;
		Nom = nom;
		this.description = description;
		this.category = category;
		this.attributs=attributs;
		this.actifs=actifs;
	}

	public FamilleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
