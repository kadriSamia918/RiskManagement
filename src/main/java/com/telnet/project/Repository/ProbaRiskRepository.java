package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.ProbaRisk;

@Repository
public interface ProbaRiskRepository extends MongoRepository<ProbaRisk,String>{

	ProbaRisk findProbaRiskById(String id);

	List<ProbaRisk> findProbaRiskByValMenace(String niveau);

}
