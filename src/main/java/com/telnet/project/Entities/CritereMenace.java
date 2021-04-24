package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="CritereMenace")
public class CritereMenace {
		
		@Id
		private String id;
		
		private String niveau;
		
		private String commentaire;
		
		public CritereMenace() {
			super();
			// TODO Auto-generated constructor stub
		}

		private String periode;
		 


		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getNiveau() {
			return niveau;
		}

		public void setNiveau(String niveau) {
			this.niveau = niveau;
		}

		public String getCommentaire() {
			return commentaire;
		}

		public void setCommentaire(String commentaire) {
			this.commentaire = commentaire;
		}

		public String getPeriode() {
			return periode;
		}

		public void setPeriode(String period) {
			this.periode = period;
		}

		public CritereMenace(String id, String niveau, String commentaire, String period) {
			super();
			this.id = id;
			this.niveau = niveau;
			this.commentaire = commentaire;
			this.periode = period;
		}
		
		
		
		

}
