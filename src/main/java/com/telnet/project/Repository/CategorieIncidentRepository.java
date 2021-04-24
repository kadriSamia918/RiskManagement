package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.telnet.project.Entities.CategorieIncident;

public interface CategorieIncidentRepository extends MongoRepository<CategorieIncident,String>{ 
	CategorieIncident	findCategorieIncidentById(String id);

}
