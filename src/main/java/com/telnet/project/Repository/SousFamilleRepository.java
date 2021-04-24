package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.SousFamille;

@Repository
public interface SousFamilleRepository  extends MongoRepository<SousFamille, String> {

	 
	   List<SousFamille> findAll();
	  
	 
	    
	    SousFamille save(SousFamille saved);



		SousFamille findSousFamilleByNom(String nomSousFamille);
	    
	
	   
}
