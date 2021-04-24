package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="SourceMenace") 
public class SourceMenace {

	@Id
	private String id; 
	
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SourceMenace(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public SourceMenace() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
