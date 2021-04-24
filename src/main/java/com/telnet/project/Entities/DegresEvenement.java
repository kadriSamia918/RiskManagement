package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="DegreEvenement")
public class DegresEvenement {
	@Id
	private String id;
	
	private String niveau;
	
	private String description;
	
	private String decision;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public DegresEvenement(String id, String niveau, String description, String decision) {
		super();
		this.id = id;
		this.niveau = niveau;
		this.description = description;
		this.decision = decision;
	}

	public DegresEvenement() {
		super();
	}
	
}
