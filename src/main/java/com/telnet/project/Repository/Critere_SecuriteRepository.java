package com.telnet.project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.Critere_Securite;

@Repository
public interface Critere_SecuriteRepository extends MongoRepository<Critere_Securite, String> {

	 
	   List<Critere_Securite> findAll();
	  
	 
	
	    
	   Critere_Securite save(Critere_Securite saved);
	    Critere_Securite findCritere_SecuriteByNom(String nom);


		Critere_Securite findByNom(String string);


		boolean existsByNom(String string);
		
}