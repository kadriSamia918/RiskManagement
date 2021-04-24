package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.DegreCriticite;

@Repository
public interface DegreCriticiteRepository extends MongoRepository<DegreCriticite,String> {

	DegreCriticite findDegreCriticiteRepositoryByDegre(int degre);

}
