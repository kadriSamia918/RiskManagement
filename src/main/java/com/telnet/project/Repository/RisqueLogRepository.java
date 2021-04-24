package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.RisqueLog;

@Repository
public interface RisqueLogRepository extends MongoRepository<RisqueLog, String> {
	List<RisqueLog> findRisqueLogByCodeRisque(String risqueId);

}
