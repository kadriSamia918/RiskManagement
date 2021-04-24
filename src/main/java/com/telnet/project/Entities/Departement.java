package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "departement")
public class Departement {
	
		@Id
		private String id;
		private String nom;
		
		
		
		public Departement() {
			super();
		}
		public Departement(String id, String nom) {
			super();
			this.id = id;
			this.nom = nom;
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
		public void setNom(String nom) {
			this.nom = nom;
		}
		
		

}
