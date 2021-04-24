package com.telnet.project.ServiceImpl;

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

import com.telnet.project.Entities.CategorieIncident;
import com.telnet.project.Entities.CritereImpactIncident;
import com.telnet.project.Entities.CritereMenace;
import com.telnet.project.Entities.Evenement;
import com.telnet.project.Entities.EvenementDetail;
import com.telnet.project.Repository.CritereImpactIncidentRepository;
import com.telnet.project.Services.CritereImpactIncidentService;

@Service
public class CritereImpactIncidentServiceImpl implements CritereImpactIncidentService {
	
	 @Autowired
	    private  CritereImpactIncidentRepository critereImpactIncidentRepository;
	 @Autowired
	 MongoTemplate mongoTemplate; 

	
	@Override
	public List<CritereImpactIncident> findAll() {
		
		return critereImpactIncidentRepository.findAll();
	}
	
	
	
	

	public Map<String, CritereImpactIncident> ajoutCritere(@Valid CritereImpactIncident CritereEntry) {
		Map<String, CritereImpactIncident> response = new HashMap<>();
		CritereImpactIncident crIn = new CritereImpactIncident();
		String niveauIncident = CritereEntry.getNiveauIncident();
		String df = CritereEntry.getDefinitionIncident();

		String pf = CritereEntry.getPfinanceIncident();
		String pj = CritereEntry.getPjuridiqueIncident();
		String pI = CritereEntry.getPimageIncident();

		crIn.setDefinitionIncident(df);

		crIn.setPfinanceIncident(pf);
		crIn.setPjuridiqueIncident(pj);
		crIn.setNiveauIncident(niveauIncident);
		crIn.setPimageIncident(pI);

		

		
	
		critereImpactIncidentRepository.save(crIn);
		 response.put("critere_Ajoutée",crIn);
		 System.out.println(response);
	  
		return response;}
	
	@Override
	public Map<String, Boolean> delete(String id) {
		
		CritereImpactIncident deleted = critereImpactIncidentRepository.findCriterImpactIncidentById(id);
	    
		
		critereImpactIncidentRepository.delete(deleted);
		
		 Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	}




	@Override
	public Map<String, Boolean> update(CritereImpactIncident critereImpact) {
		Map<String, Boolean> response = new HashMap<>();
		CritereImpactIncident critereImpactSaved = 
				critereImpactIncidentRepository.findCriterImpactIncidentByNiveauIncident(critereImpact.getNiveauIncident());
		Query query = new Query();
		query.addCriteria(Criteria.where("niveau").is(critereImpactSaved.getNiveauIncident()));
		Update update = new Update();
		update.set("definitionIncident",critereImpact.getDefinitionIncident());
		update.set("pFinanceIncident", critereImpact.getPfinanceIncident());
		update.set("pJuridiqueIncident", critereImpact.getPjuridiqueIncident());
		update.set("pImageIncident", critereImpact.getPimageIncident());
		mongoTemplate.updateFirst(query, update, CritereMenace.class);
		response.put("success", Boolean.TRUE);
		return response;
	}

	
	

}
