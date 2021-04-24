package com.telnet.project.Entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="CritereImpactIncident")
public class CritereImpactIncident {
	
	@Id
	private String id;
	
	private String niveauIncident;
	
	private String definitionIncident;
	
	private String pfinanceIncident;
	
	private String pjuridiqueIncident;
	
	private String pimageIncident;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNiveauIncident() {
		return niveauIncident;
	}

	public void setNiveauIncident(String niveauIncident) {
		this.niveauIncident = niveauIncident;
	}

	public String getDefinitionIncident() {
		return definitionIncident;
	}

	public void setDefinitionIncident(String definitionIncident) {
		this.definitionIncident = definitionIncident;
	}

	public String getPfinanceIncident() {
		return pfinanceIncident;
	}

	public void setPfinanceIncident(String pfinanceIncident) {
		this.pfinanceIncident = pfinanceIncident;
	}

	public String getPjuridiqueIncident() {
		return pjuridiqueIncident;
	}

	public void setPjuridiqueIncident(String pjuridiqueIncident) {
		this.pjuridiqueIncident = pjuridiqueIncident;
	}

	public String getPimageIncident() {
		return pimageIncident;
	}

	public void setPimageIncident(String pimageIncident) {
		this.pimageIncident = pimageIncident;
	}

	public CritereImpactIncident(String id, String niveauIncident, String definitionIncident, String pfinanceIncident,
			String pjuridiqueIncident, String pimageIncident) {
		super();
		this.id = id;
		this.niveauIncident = niveauIncident;
		this.definitionIncident = definitionIncident;
		this.pfinanceIncident = pfinanceIncident;
		this.pjuridiqueIncident = pjuridiqueIncident;
		this.pimageIncident = pimageIncident;
	}

	public CritereImpactIncident() {
		super();
	}

	 
	


}
