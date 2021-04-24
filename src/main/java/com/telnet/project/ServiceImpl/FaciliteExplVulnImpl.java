package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Echelle_Criticite;
import com.telnet.project.Entities.FaciliteExplVuln;
import com.telnet.project.Entities.ProbaRisk;
import com.telnet.project.Entities.Risque;
import com.telnet.project.Repository.FaciliteExplVulnRepository;
import com.telnet.project.Repository.ProbaRiskRepository;
import com.telnet.project.Services.FaciliteExplVulnService;
@Service
public class FaciliteExplVulnImpl implements FaciliteExplVulnService {
	
	 @Autowired
	    private  FaciliteExplVulnRepository faciliteExplVulnRepository;
	 
	 @Autowired
		private  ProbaRiskRepository probaRiskRepository;
	 
	 @Autowired
	 MongoTemplate mongoTemplate; 
	 
	
	@Override
	public Map<String, Boolean> delete(String id) {
		FaciliteExplVuln deleted = faciliteExplVulnRepository.findFaciliteExplVulnById(id);
		List<ProbaRisk> listProba = new ArrayList<ProbaRisk>();
		listProba =  probaRiskRepository.findAll(); 
		for(ProbaRisk prb:listProba) {
			if(prb.getFacilExpVul().getId().equals(deleted.getId())){
				probaRiskRepository.delete(prb);
			}
		}
		faciliteExplVulnRepository.delete(deleted);
		 Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	}

	@Override
	public List<FaciliteExplVuln> findAll() {
		return faciliteExplVulnRepository.findAll();	}

	@Override
	public Map<String, Boolean> update(FaciliteExplVuln faciliteExplVuln) {
		Map<String, Boolean> response = new HashMap<>();
		FaciliteExplVuln faciliteExplVulnSaved = faciliteExplVulnRepository.findFaciliteExplVulnByNiveau(faciliteExplVuln.getNiveau());
		Query query = new Query();
		query.addCriteria(Criteria.where("niveau").is(faciliteExplVulnSaved.getNiveau()));
		Update update = new Update();
		update.set("commentaire", faciliteExplVuln.getCommentaire());
		
		mongoTemplate.updateFirst(query, update, FaciliteExplVuln.class);
		response.put("success", Boolean.TRUE);
		return response;
	}

	public FaciliteExplVuln ajout(FaciliteExplVuln faciliteExplVulnEntry) {
		
		FaciliteExplVuln fv = new FaciliteExplVuln();
		fv.setNiveau(faciliteExplVulnEntry.getNiveau());
		fv.setCommentaire(faciliteExplVulnEntry.getCommentaire());
		return faciliteExplVulnRepository.save(fv);
	
	}

}
