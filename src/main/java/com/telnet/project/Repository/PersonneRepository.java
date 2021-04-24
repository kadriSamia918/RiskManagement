package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.Personne;
@Repository
public interface PersonneRepository extends MongoRepository<Personne, String> {

	 
	   List<Personne> findAll();
  
	    Personne save(Personne saved);

		Personne findPersonneByName(String name);

		Personne findPersonneById(String id);
	    
	    
}
