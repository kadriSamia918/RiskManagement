package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.telnet.project.Entities.CritereImpactIncident;

public interface CritereImpactIncidentRepository extends  MongoRepository<CritereImpactIncident,String>{

	CritereImpactIncident findCriterImpactIncidentById(String id);

	CritereImpactIncident findCriterImpactIncidentByNiveauIncident(String id);
	
	

}
