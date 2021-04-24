package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.CritereImpact;

@Repository
public interface CritereImpactRepository extends  MongoRepository<CritereImpact,String>{

	CritereImpact findCriterImpactById(String id);

	CritereImpact findCriterImpactByNiveau(String id);
	
	

}
