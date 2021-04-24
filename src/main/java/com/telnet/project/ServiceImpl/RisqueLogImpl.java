package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Change;
import com.telnet.project.Entities.Evenement;
import com.telnet.project.Entities.RisqueLog;
import com.telnet.project.Repository.RisqueLogRepository;
import com.telnet.project.Services.RisqueLogService;

@Service
public class RisqueLogImpl implements RisqueLogService {
	
	@Autowired
	private RisqueLogRepository risqueLogRepository;
	
	public  Map<String, Boolean>  saveLog(RisqueLog log){
		RisqueLog saved = new RisqueLog();
		saved.setCodeRisque(log.getCodeRisque());
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
		risqueLogRepository.save(saved);
		 Map<String, Boolean> response = new HashMap<>();
	        response.put("savedLog", Boolean.TRUE);
	        return response;
		
	}
	@Override
	
	public List<RisqueLog> findLogByRisqueId(String risqueId){
		System.out.println(risqueId);
		return risqueLogRepository.findRisqueLogByCodeRisque(risqueId);
		
		
	}

	@Override
	public List<RisqueLog> findAll() {
		return risqueLogRepository.findAll();	
	}


	

}
