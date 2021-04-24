package com.telnet.project.Repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.AttributActif;

@Repository
public interface AttributActifRepository extends MongoRepository<AttributActif, String> {
	
	 //void delete(Optional<AttributActif> deleted);
	 
	   List<AttributActif> findAll();
	  
	 
	  /// Optional<AttributActif> findOne(Long id);
	    
	    AttributActif save(AttributActif saved);
	    
	   AttributActif findAttributActifByNom(String nom);
	   
	  AttributActif findAttributActifById(String id);
}
