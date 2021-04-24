package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.TypeMenace;

@Repository
public interface TypeMenaceRepository  extends MongoRepository<TypeMenace, String>  {

	TypeMenace findTypeMenaceById(String id);
	TypeMenace findTypeMenaceByDescription(String description);

}
