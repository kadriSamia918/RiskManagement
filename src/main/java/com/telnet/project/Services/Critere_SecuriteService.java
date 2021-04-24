package com.telnet.project.Services;

import java.util.List;

import com.telnet.project.Entities.Critere_Securite;
import com.telnet.project.Entities.Echelle_Criticite;


public interface Critere_SecuriteService {

	Critere_Securite createCritere_SecuriteConfidentialite();
	Critere_Securite createCritere_SecuriteIntegrite(); 
	Critere_Securite createCritere_SecuriteDisponibilite();
	void delete(Critere_Securite critere_Securite);
 
    List<Critere_Securite> findAll();
 

    Critere_Securite update(Critere_Securite critere_Securite);
	List<Echelle_Criticite> updateCritere_SecuriteConfidentialite(List<Echelle_Criticite> echelle_Criticite);
	List<Echelle_Criticite> updateCritere_SecuriteIntegrite(List<Echelle_Criticite> echelle_Criticite);
	List<Echelle_Criticite> updateCritere_SecuriteDisponibilite(List<Echelle_Criticite> echelle_Criticite);

}
