package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Change;
import com.telnet.project.Entities.EvenementLog;
import com.telnet.project.Repository.EvenementLogRepository;
import com.telnet.project.Services.EvenementLogService;
@Service
public class EvenementLogImpl implements EvenementLogService {
	@Autowired
	private EvenementLogRepository evenementLogRepository;
	
	public  Map<String, Boolean>  saveLog(EvenementLog log){
		EvenementLog saved = new EvenementLog();
		saved.setCodeEvenement(log.getCodeEvenement());
		saved.setSaverId(log.getSaverId());
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
	    evenementLogRepository.save(saved);
		 Map<String, Boolean> response = new HashMap<>();
	        response.put("savedLog", Boolean.TRUE);
	        return response;
		
	}
	@Override
	public List<EvenementLog> findLogByEvenementId(String evenementId){
		System.out.println(evenementId);
		return evenementLogRepository.findEvenementLogByCodeEvenement(evenementId);
		
		
	}

	

	
	
	
	

}
