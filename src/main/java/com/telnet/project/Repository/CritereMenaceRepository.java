package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.CritereMenace;
import com.telnet.project.Entities.FaciliteExplVuln;


@Repository
public interface CritereMenaceRepository extends  MongoRepository<CritereMenace, String>{

	CritereMenace findCritereMenaceByNiveau(String niveau);

	CritereMenace findCritereMenaceById(String id);

	
	
	

	
}
