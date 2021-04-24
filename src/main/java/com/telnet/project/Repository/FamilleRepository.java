package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.Category;
import com.telnet.project.Entities.Famille;


@Repository
public interface FamilleRepository  extends MongoRepository<Famille, String> {

	 
	   List<Famille> findAll();
	  
	 
	    
	    Famille save(Famille saved);
	    
	    //List findFamilleByNom(String nom);
	    List<Famille> findFamilleByCategory(Category category);



		Famille findFamilleByNom(String nom);



		Famille findFamilleById(String id_Famille);
	
	   
}
