package com.telnet.project.Entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="IncidentLog")

public class IncidentLog {
	@Id
	private String id;
	private String codeIncident;
	private String saverId;
	private String dateDeDetection;
    private String codeEvenement;
	private String detecteur;
	private List<Change> changes;
	public String getId() {
		return id;
	}
	
	
	
	
	public String getDateDeDetection() {
		return dateDeDetection;
	}




	public void setDateDeDetection(String dateDeDetection) {
		this.dateDeDetection = dateDeDetection;
	}




	public String getDetecteur() {
		return detecteur;
	}

	public void setDetecteur(String detecteur) {
		this.detecteur = detecteur;
	}
	
	

	public String getCodeEvenement() {
		return codeEvenement;
	}




	public void setCodeEvenement(String codeEvenement) {
		this.codeEvenement = codeEvenement;
	}




	public void setId(String id) {
		this.id = id;
	}
	public String getCodeIncident() {
		return codeIncident;
	}
	public void setCodeIncident(String codeIncident) {
		this.codeIncident = codeIncident;
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

	public IncidentLog() {
		super();
	}




	public IncidentLog(String id, String codeIncident, String saverId, String dateDeDetection, String detecteur,
			List<Change> changes) {
		super();
		this.id = id;
		this.codeIncident = codeIncident;
		this.saverId = saverId;
		this.dateDeDetection = dateDeDetection;
		this.detecteur = detecteur;
		this.changes = changes;
	}


	

}
