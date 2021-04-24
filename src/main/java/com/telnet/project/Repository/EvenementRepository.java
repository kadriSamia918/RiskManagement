package com.telnet.project.Repository;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.telnet.project.Entities.CategorieIncident;
import com.telnet.project.Entities.Evenement;



public interface EvenementRepository extends MongoRepository<Evenement,String> {

	 List<Evenement> findAll();

	Optional<Evenement> findById(String id);

	Evenement findEvenementByCode(String code);

	List<Evenement> findEvenementByDetecteur(String detecteur);

	Evenement findEvenementById(String evenement);

	List<Evenement> findEvenementByCategorie(@Valid CategorieIncident categorie);


	
	
		

}
