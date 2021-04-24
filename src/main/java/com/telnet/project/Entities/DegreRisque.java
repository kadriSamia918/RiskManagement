package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="DegreRisque")
public class DegreRisque {
	
	@Id
	private String id;
	
	private String niveau;
	
	private String description;
	
	private String decision;

	public String getId() {
		return id;
	}

	public DegreRisque(String id, String niveau, String description, String decision) {
		super();
		this.id = id;
		this.niveau = niveau;
		this.description = description;
		this.decision = decision;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}



	public DegreRisque() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
