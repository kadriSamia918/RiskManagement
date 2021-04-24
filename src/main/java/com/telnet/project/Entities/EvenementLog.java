package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="EvenementLog")
public class EvenementLog {
	
	@Id
	private String id;
	private String codeEvenement;
	private String saverId;
	private List<Change> changes;
	

	public EvenementLog() {
		super();
	}
	public EvenementLog(String id, String codeEvenement, String saverId, List<Change> changes) {
		super();
		this.id = id;
		this.codeEvenement = codeEvenement;
		this.saverId = saverId;
		this.changes = changes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCodeEvenement() {
		return codeEvenement;
	}
	public void setCodeEvenement(String codeEvenement) {
		this.codeEvenement = codeEvenement;
	}
	public String getSaverId() {
		return saverId;
	}
	public void setSaverId(String saverId) {
		this.saverId = saverId;
	}
	public List<Change> getChanges() {
		return changes;
	}
	public void setChanges(List<Change> changes) {
		this.changes = changes;
	}
	

	
	

}
