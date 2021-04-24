package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.Menace;

@Repository
public interface MenaceRepository extends MongoRepository<Menace,String> {

	Menace findMenaceByCode(String code);

	Menace findMenaceById(String id);

	 List<Menace>findMenaceByFamille(String famille);
	

}
