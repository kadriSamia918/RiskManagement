package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.telnet.project.Entities.DegresEvenement;

public interface DegresEvenementRepository extends MongoRepository<DegresEvenement, String>  {

}
