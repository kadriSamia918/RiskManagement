package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="CritereImpact")
public class CritereImpact {
	
	@Id
	private String id;
	
	private String niveau;
	
	private String definition;
	
	private String pfinance;
	
	private String pjuridique;
	
	private String pimage;

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

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getPFinance() {
		return pfinance;
	}

	public void setPFinance(String p_Finance) {
		pfinance = p_Finance;
	}

	public String getPJuridique() {
		return pjuridique;
	}

	public void setPJuridique(String p_Juridique) {
		pjuridique = p_Juridique;
	}

	public String getPImage() {
		return pimage;
	}

	public void setPImage(String p_Image) {
		pimage = p_Image;
	}

	public CritereImpact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CritereImpact(String id, String niveau, String definition, String p_Finance, String p_Juridique,
			String p_Image) {
		super();
		this.id = id;
		this.niveau = niveau;
		this.definition = definition;
		pfinance = p_Finance;
		pjuridique = p_Juridique;
		pimage = p_Image;
	}
	 
	


}
