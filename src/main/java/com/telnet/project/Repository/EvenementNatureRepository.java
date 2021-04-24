package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.telnet.project.Entities.EvenementNature;


public interface EvenementNatureRepository extends MongoRepository<EvenementNature,String> {

}
