package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.ValorisationRisk;

@Repository
public interface ValorisationRiskRepository extends MongoRepository<ValorisationRisk,String> {
	
	

}
