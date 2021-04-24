package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.EvenementLog;
@Repository
public interface EvenementLogRepository extends MongoRepository<EvenementLog, String> {

	List<EvenementLog> findEvenementLogByCodeEvenement(String evenementId);

}
