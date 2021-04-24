package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Change;
import com.telnet.project.Entities.EvenementDetail;
import com.telnet.project.Entities.IncidentLog;
import com.telnet.project.Entities.RisqueLog;
import com.telnet.project.Repository.IncidentLogRepository;
import com.telnet.project.Services.IncidentLogService;
 @Service
public class IncidentLogImp implements IncidentLogService {
	@Autowired
	private IncidentLogRepository incidentLogRepository;
	public Map<String, IncidentLog> saveLog(@Valid IncidentLog log) {
		IncidentLog saved = new IncidentLog();
		saved.setCodeIncident(log.getCodeIncident());
		System.out.println(saved.getCodeIncident());
		saved.setSaverId(log.getSaverId());
		saved.setDetecteur(log.getDetecteur());
		saved.setDateDeDetection(log.getDateDeDetection());
	
		saved.setCodeEvenement(log.getCodeEvenement());

		List <Change> savedChanges =new ArrayList<Change>();
		for (Change cahnge:log.getChanges())		{
			Change savedChange = new Change();
			savedChange.setColumn(cahnge.getColumn());
			savedChange.setNewValue(cahnge.getNewValue());
			savedChange.setPrevValue(cahnge.getPrevValue());
			savedChange.setDate(cahnge.getDate());
			 savedChanges.add(savedChange);
			
		}
	    saved.setChanges(savedChanges);
	    incidentLogRepository.save(saved);
		 Map<String, IncidentLog> response = new HashMap<>();
	        response.put("savedLog", saved);
	        return response;
		
	}
	
	@Override
	public List<IncidentLog> findLogByIncidentId(String incidentId){
		System.out.println(incidentId);
		return incidentLogRepository.findIncidentLogByCodeIncident(incidentId);
		
		
	}

	

	
	

}
