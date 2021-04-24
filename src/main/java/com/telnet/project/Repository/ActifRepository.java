package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Personne;


@Repository
public interface ActifRepository extends MongoRepository<Actif, String> {
	
	 //void delete(Optional<AttributActif> deleted);
	 
	   List<Actif> findAll();
	  
	 
	  /// Optional<AttributActif> findOne(Long id);
	    
	    Actif save(Actif saved);


		List<Actif> findActifByProprietaire(Personne proprietaire);


		Actif findActifById(String id);
	    
	  // Actif findAttributActifByNom(String nom);
	   
	  // AttributActif findById(String id);
}
