package com.telnet.project.Repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.telnet.project.Entities.Echelle_Criticite;

@Repository
public interface Echelle_CriticiteRepository extends MongoRepository<Echelle_Criticite, String> {
		
		 //void delete(Optional<AttributActif> deleted);
		 
		   List<Echelle_Criticite> findAll();
		  
		 
		  /// Optional<AttributActif> findOne(Long id);
		    
		   Echelle_Criticite save(Echelle_Criticite saved);
		   
		   Echelle_Criticite findEchelle_CriticiteById(String id);
		   
		   Echelle_Criticite findEchelle_CriticiteByDegre(String degre);
		   

		boolean existsByNom(String nom);


		Echelle_Criticite findEchelleById(String id);


	

}
