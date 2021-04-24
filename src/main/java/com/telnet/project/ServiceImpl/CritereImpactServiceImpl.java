package com.telnet.project.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.CritereImpact;
import com.telnet.project.Entities.CritereMenace;
import com.telnet.project.Repository.CritereImpactRepository;
import com.telnet.project.Services.CritereImpactService;

@Service
public class CritereImpactServiceImpl implements CritereImpactService {
	
	 @Autowired
	    private  CritereImpactRepository critereImpactRepository;
	 @Autowired
	 MongoTemplate mongoTemplate; 

	@Override
	public Map<String, Boolean> delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CritereImpact> findAll() {
		
		return critereImpactRepository.findAll();
	}

	@Override
	public Map<String, Boolean> update(CritereImpact critereImpact) {
		Map<String, Boolean> response = new HashMap<>();
		CritereImpact critereImpactSaved = critereImpactRepository.findCriterImpactByNiveau(critereImpact.getNiveau());
		Query query = new Query();
		query.addCriteria(Criteria.where("niveau").is(critereImpactSaved.getNiveau()));
		Update update = new Update();
		update.set("definition",critereImpact.getDefinition());
		update.set("pFinance", critereImpact.getPFinance());
		update.set("pJuridique", critereImpact.getPJuridique());
		update.set("pImage", critereImpact.getPImage());
		mongoTemplate.updateFirst(query, update, CritereMenace.class);
		response.put("success", Boolean.TRUE);
		return response;
	}

}
