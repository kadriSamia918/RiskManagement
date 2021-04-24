package com.telnet.project.Entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection="ValorisationRisk")
public class ValorisationRisk {
	
	@Id
	private String id;
	
	@JsonProperty(access = JsonProperty.Access.AUTO)
	@DBRef
	@Field("criterImpact")
	private CritereImpact criterImpact;
	
	@JsonProperty(access = JsonProperty.Access.AUTO)
	@DBRef
	@Field("probaRiskList")
	private List<ProbaRisk> probaRiskList;
	
	@JsonProperty(access = JsonProperty.Access.AUTO)
	@DBRef
	@Field("degreRisk")
	private DegreRisque degreRisk;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CritereImpact getCriterImpact() {
		return criterImpact;
	}

	public void setCriterImpact(CritereImpact criterImpact) {
		this.criterImpact = criterImpact;
	}

	public List<ProbaRisk> getProbaRiskList() {
		return probaRiskList;
	}

	public void setProbaRiskList(List<ProbaRisk> probaRiskList) {
		this.probaRiskList = probaRiskList;
	}

	public DegreRisque getDegreRisk() {
		return degreRisk;
	}

	public void setDegreRisk(DegreRisque degreRisk) {
		this.degreRisk = degreRisk;
	}

	public ValorisationRisk(String id, CritereImpact criterImpact, List<ProbaRisk> probaRiskList,
			DegreRisque degreRisk) {
		super();
		this.id = id;
		this.criterImpact = criterImpact;
		this.probaRiskList = probaRiskList;
		this.degreRisk = degreRisk;
	}

	public ValorisationRisk() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
