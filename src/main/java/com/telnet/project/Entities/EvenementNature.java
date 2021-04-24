package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="evenementNature")

public class EvenementNature {
	@Id
	String id;
	String nature;

	public EvenementNature() {
		super();
	}
	public EvenementNature(String id, String nature) {
		super();
		this.id = id;
		this.nature = nature;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	

}
