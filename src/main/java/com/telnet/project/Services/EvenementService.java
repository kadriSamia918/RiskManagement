package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.telnet.project.Entities.CategorieIncident;
import com.telnet.project.Entities.Evenement;
import com.telnet.project.Entities.Personne;



public interface EvenementService {
	List<Evenement> findAll();

	Evenement update(Evenement evenement);

	List<Evenement> findEvenementByDetecteur(String detecteur);

	Evenement setInactive(Evenement evenement);


	List<Evenement> findEvenementByCategory(@Valid CategorieIncident categorie);

	Evenement updateType(Evenement evenement);

	Evenement updateType2(Evenement evenement);



}
