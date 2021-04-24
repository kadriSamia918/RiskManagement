package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.DegreRisque;

@Repository
public interface DegreRisqueRepository  extends MongoRepository<DegreRisque,String> {

	DegreRisque findDegreRisqueByNiveau(String niveau);

}
