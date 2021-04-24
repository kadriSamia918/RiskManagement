package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.FaciliteExplVuln;

@Repository
public interface FaciliteExplVulnRepository extends  MongoRepository<FaciliteExplVuln, String>{

	FaciliteExplVuln findFaciliteExplVulnByNiveau(String niveau);

	FaciliteExplVuln findFaciliteExplVulnById(String id);
	
	

	
}



