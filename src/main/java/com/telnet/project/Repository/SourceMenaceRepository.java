package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.SourceMenace;

@Repository
public interface SourceMenaceRepository extends MongoRepository<SourceMenace, String>  {

	SourceMenace findSourceMenaceByDescription(String description);
	SourceMenace findSourceMenaceById(String id);

}
