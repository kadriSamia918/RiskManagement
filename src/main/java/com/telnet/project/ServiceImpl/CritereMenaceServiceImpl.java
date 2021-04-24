package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.CritereMenace;
import com.telnet.project.Entities.FaciliteExplVuln;
import com.telnet.project.Entities.ProbaRisk;
import com.telnet.project.Repository.CritereMenaceRepository;
import com.telnet.project.Repository.ProbaRiskRepository;
import com.telnet.project.Services.CritereMenaceService;

@Service
public class CritereMenaceServiceImpl implements CritereMenaceService {
	
	 @Autowired
	    private  CritereMenaceRepository critereMenaceRepository;
	 
	 @Autowired
		private  ProbaRiskRepository probaRiskRepository;
	 
	 
	 @Autowired
	 MongoTemplate mongoTemplate; 

	@Override
	public Map<String, Boolean> delete(String id) {
		CritereMenace deleted = critereMenaceRepository.findCritereMenaceById(id);
		List<ProbaRisk> listProba = new ArrayList<ProbaRisk>();
		listProba =  probaRiskRepository.findAll(); 
		for(ProbaRisk prb:listProba) {
			if(prb.getValMenace().getId().equals(deleted.getId())){
				probaRiskRepository.delete(prb);
			}
		}
		critereMenaceRepository.delete(deleted);
		 Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	}

	@Override
	public List<CritereMenace> findAll() {
	
		return critereMenaceRepository.findAll();
	}


	@Override
	public Map<String, Boolean> update(CritereMenace critereMenace) {
		Map<String, Boolean> response = new HashMap<>();
		CritereMenace critereMenaceSaved = critereMenaceRepository.findCritereMenaceByNiveau(critereMenace.getNiveau());
		Query query = new Query();
		query.addCriteria(Criteria.where("niveau").is(critereMenaceSaved.getNiveau()));
		Update update = new Update();
		update.set("commentaire", critereMenace.getCommentaire());
		update.set("periode", critereMenace.getPeriode());
		mongoTemplate.updateFirst(query, update, CritereMenace.class);
		response.put("success", Boolean.TRUE);
		return response;
	}


	public CritereMenace ajout(@Valid CritereMenace critereMenaceEntry) {
		CritereMenace fv = new CritereMenace();
		fv.setNiveau(critereMenaceEntry.getNiveau());
		fv.setCommentaire(critereMenaceEntry.getCommentaire());
		fv.setPeriode(critereMenaceEntry.getPeriode());
		return critereMenaceRepository.save(fv);
	}

}
