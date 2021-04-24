package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="FaciliteExpVuln")
public class FaciliteExplVuln {
	
	@Id
	private String id;
	
	private String niveau;
	
	private String commentaire;

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

	public FaciliteExplVuln(String id, String niveau, String commentaire) {
		super();
		this.id = id;
		this.niveau = niveau;
		this.commentaire = commentaire;
	}

	public FaciliteExplVuln() {
		super();
	}
	

}
