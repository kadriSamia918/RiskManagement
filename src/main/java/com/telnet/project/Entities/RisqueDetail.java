package com.telnet.project.Entities;

import java.util.List;

public class RisqueDetail {

	private String id;
	
	private FaciliteExplVuln vulnerabilite;
	
	private CritereMenace critereMenace;
	
	private ProbaRisk probaRisq;
	
	private List<DegreCriticite> pF;
	
	private List<DegreCriticite> pJ;
	
	private List<DegreCriticite> pI;
	
	private CritereImpact criterImpact;
	
	private DegreRisque degreRisque;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FaciliteExplVuln getVulnerabilite() {
		return vulnerabilite;
	}

	public void setVulnerabilite(FaciliteExplVuln vulnerabilite) {
		this.vulnerabilite = vulnerabilite;
	}

	public CritereMenace getCritereMenace() {
		return critereMenace;
	}

	public void setCritereMenace(CritereMenace critereMenace) {
		this.critereMenace = critereMenace;
	}

	public ProbaRisk getProbaRisq() {
		return probaRisq;
	}

	public void setProbaRisq(ProbaRisk probaRisq) {
		this.probaRisq = probaRisq;
	}


	

	public CritereImpact getCriterImpact() {
		return criterImpact;
	}

	public void setCriterImpact(CritereImpact criterImpact) {
		this.criterImpact = criterImpact;
	}

	public DegreRisque getDegreRisque() {
		return degreRisque;
	}

	public void setDegreRisque(DegreRisque degreRisque) {
		this.degreRisque = degreRisque;
	}

	public RisqueDetail(String id, FaciliteExplVuln vulnerabilite, CritereMenace critereMenace, ProbaRisk probaRisq,
			List<DegreCriticite> pF, List<DegreCriticite> pJ, List<DegreCriticite> pI, CritereImpact criterImpact,
			DegreRisque degreRisque) {
		super();
		this.id = id;
		this.vulnerabilite = vulnerabilite;
		this.critereMenace = critereMenace;
		this.probaRisq = probaRisq;
		this.pF = pF;
		this.pJ = pJ;
		this.pI = pI;
		this.criterImpact = criterImpact;
		this.degreRisque = degreRisque;
	}

	public List<DegreCriticite> getpF() {
		return pF;
	}

	public void setpF(List<DegreCriticite> pF) {
		this.pF = pF;
	}

	public List<DegreCriticite> getpJ() {
		return pJ;
	}

	public void setpJ(List<DegreCriticite> pJ) {
		this.pJ = pJ;
	}

	public List<DegreCriticite> getpI() {
		return pI;
	}

	public void setpI(List<DegreCriticite> pI) {
		this.pI = pI;
	}

	public RisqueDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
