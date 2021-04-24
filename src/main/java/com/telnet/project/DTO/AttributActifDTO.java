package com.telnet.project.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import com.telnet.project.Entities.AttributActif;


	public class AttributActifDTO {
		
		@Id
	    private String id_AttributActif;
	    
	    @NotEmpty
	    @Size(max = AttributActif.MAX_LENGTH_TITLE)
	    private String nom;
	    
	    @Size(max = AttributActif.MAX_LENGTH_DESCRIPTION)
	    private String type;

		public String getId_AttributActif() {
			return id_AttributActif;
		}

		public void setId_AttributActif(String id_AttributActif) {
			this.id_AttributActif = id_AttributActif;
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
			this.type= type;
		}

		public AttributActifDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

	}
