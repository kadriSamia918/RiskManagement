package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.EvenementDetail;
import com.telnet.project.Entities.IncidentLog;
import com.telnet.project.Entities.RisqueLog;
@Repository
public interface IncidentLogRepository extends MongoRepository<IncidentLog, String> {


	List<IncidentLog> findIncidentLogByCodeIncident(String incidentId);


	List<IncidentLog> findIncidentLogById(String incidentId);

}