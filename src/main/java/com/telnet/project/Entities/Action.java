package com.telnet.project.Entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection="action")
public class Action {
	
	@Id
	private String id;
	private String categorie;
	private String codeAction;
	private String source;
	
	private String traitement;
	
	private List<String> codesRisque;
	private List<String> codesIncident;

	
	private Date savingDate;
	
	private String saverId;
	@DBRef
	@JsonProperty(access = JsonProperty.Access.AUTO)
	private Personne responsableMO;
	
	private String dG;
	
	private String reason;
	
	private String delai;
	private String delaiCloture;

	private String delaiMesureEff;
	
	private int efficacite;
	
	private String commentaireEfficacite;
	
	private int etatRealisation;
	

	public int getEtatRealisation() {
		return etatRealisation;
	}

	public void setEtatRealisation(int etatRealisation) {
		this.etatRealisation = etatRealisation;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<String> getCodesIncident() {
		return codesIncident;
	}

	public void setCodesIncident(List<String> codesIncident) {
		this.codesIncident = codesIncident;
	}

	public String getDelaiCloture() {
		return delaiCloture;
	}

	public void setDelaiCloture(String delaiCloture) {
		this.delaiCloture = delaiCloture;
	}

	public String getCodeAction() {
		return codeAction;
	}

	public void setCodeAction(String codeAction) {
		this.codeAction = codeAction;
	}

	public String getTraitement() {
		return traitement;
	}

	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}

	public List<String> getCodesRisque() {
		return codesRisque;
	}

	public void setCodesRisque(List<String> codesRisque) {
		this.codesRisque = codesRisque;
	}

	public Personne getResponsableMO() {
		return responsableMO;
	}

	public void setResponsableMO(Personne responsableMO) {
		this.responsableMO = responsableMO;
	}

	public String getdG() {
		return dG;
	}

	public void setdG(String dG) {
		this.dG = dG;
	}

	public String getDelai() {
		return delai;
	}

	public void setDelai(String delai) {
		this.delai = delai;
	}

	public String getDelaiMesureEff() {
		return delaiMesureEff;
	}

	public void setDelaiMesureEff(String delaiMesureEff) {
		this.delaiMesureEff = delaiMesureEff;
	}

	public int getEfficacite() {
		return efficacite;
	}

	public void setEfficacite(int efficacite) {
		this.efficacite = efficacite;
	}

	public String getCommentaireEfficacite() {
		return commentaireEfficacite;
	}

	public void setCommentaireEfficacite(String commentaireEfficacite) {
		this.commentaireEfficacite = commentaireEfficacite;
	}

	public Action() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getSavingDate() {
		return savingDate;
	}

	public void setSavingDate(Date savingDate) {
		this.savingDate = savingDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	



	public Action(String id, String codeAction, String source, String traitement, String categorie,List<String> codesRisque,
			List<String> codesIncident, Date savingDate, String saverId, Personne responsableMO, String dG,
			String reason, String delai, String delaiCloture, String delaiMesureEff, int efficacite,
			String commentaireEfficacite, int etatRealisation) {
		super();
		this.id = id;
		this.codeAction = codeAction;
		this.categorie=categorie;
		this.traitement = traitement;
		this.codesRisque = codesRisque;
		this.codesIncident = codesIncident;
		this.savingDate = savingDate;
		this.saverId = saverId;
		this.responsableMO = responsableMO;
		this.dG = dG;
		this.reason = reason;
		this.delai = delai;
		this.delaiCloture = delaiCloture;
		this.delaiMesureEff = delaiMesureEff;
		this.efficacite = efficacite;
		this.commentaireEfficacite = commentaireEfficacite;
		this.etatRealisation = etatRealisation;
	}

	public String getSaverId() {
		return saverId;
	}

	public void setSaverId(String saverId) {
		this.saverId = saverId;
	}
	


	
	

}


