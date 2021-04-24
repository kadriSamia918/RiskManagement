package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection="ProbaRisk")

public class ProbaRisk {
	
	@Id
	private String id ;
	
	@DBRef
    @JsonProperty(access = JsonProperty.Access.AUTO)
	@Field("valMenace")
	private CritereMenace valMenace;
	@DBRef
    @JsonProperty(access = JsonProperty.Access.AUTO)
	@Field("facilExpVul")
	private   FaciliteExplVuln facilExpVul;
	@DBRef
    @JsonProperty(access = JsonProperty.Access.AUTO)
	@Field("degreCriticite")
	private DegreCriticite  degreCriticite; 
	
	

	public ProbaRisk() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CritereMenace getValMenace() {
		return valMenace;
	}

	public void setValMenace(CritereMenace valMenace) {
		this.valMenace = valMenace;
	}

	

	public DegreCriticite getDegreCriticite() {
		return degreCriticite;
	}

	public void setDegreCriticite(DegreCriticite degreCriticite) {
		this.degreCriticite = degreCriticite;
	}

	public FaciliteExplVuln getFacilExpVul() {
		return facilExpVul;
	}

	public void setFacilExpVul(FaciliteExplVuln facilExpVul) {
		this.facilExpVul = facilExpVul;
	}

	public ProbaRisk(String id, CritereMenace valMenace, FaciliteExplVuln facilExpVul, DegreCriticite degreCriticite) {
		super();
		this.id = id;
		this.valMenace = valMenace;
		this.facilExpVul = facilExpVul;
		this.degreCriticite = degreCriticite;
	}


}
