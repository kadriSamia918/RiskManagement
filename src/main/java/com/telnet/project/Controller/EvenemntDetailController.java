package com.telnet.project.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Evenement;
import com.telnet.project.Entities.EvenementDetail;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.IncidentImpact;
import com.telnet.project.Entities.Risque;
import com.telnet.project.Entities.Statistique;
import com.telnet.project.Repository.EvenementDetailRepository;
import com.telnet.project.ServiceImpl.EvenementDetailServiceImp;

@Component
@RestController
@RequestMapping("/api/pfe")
public class EvenemntDetailController {
	
	private EvenementDetailServiceImp service;
	private EvenementDetailRepository evenementDetailRepository;
	
	
	@Autowired
	 public EvenemntDetailController(EvenementDetailServiceImp service,
			EvenementDetailRepository evenementDetailRepository) {
		super();
		this.service = service;
		this.evenementDetailRepository = evenementDetailRepository;
	}
	  @RequestMapping(value = "/getIncidentByCode/{code}", method = RequestMethod.GET)
	  EvenementDetail findIncidentByCode(@PathVariable String code) {
	          return service.getIncidentById(code);
	}
	  @RequestMapping(method = RequestMethod.GET,value = "/incidentParMois")
	   List<IncidentImpact> IncidentParMois() {
	    	
	        return service.IncidentParMois();
	        
	    }
	   

	@RequestMapping(method = RequestMethod.POST,value = "/ajoutIncident")
	   Map<String, EvenementDetail> ajoutEvenement(@RequestBody @Valid EvenementDetail EvenementEntry) {
	        return service.ajoutDetailEvenement(EvenementEntry);
	    }
	
	
	
	  @RequestMapping(value = "/getIncidentByImpact/{impact}", method = RequestMethod.GET)
	  List<EvenementDetail> findIncidentByImpact(@PathVariable String impact) {
	          return service.getIncidentByImpact(impact);
	}
	
	 @RequestMapping(method = RequestMethod.GET,value = "/afficherIncident")
	   List<EvenementDetail> afficherEvenementDetail() {
	        return service.findQuery();
	    }
	 @PutMapping( "/SetEtatEvenement/{id}")
	 public ResponseEntity < EvenementDetail > setEtatEvenement(@PathVariable String id, @RequestBody EvenementDetail evenement) {
		 evenement.setId(id);
		   return ResponseEntity.ok().body(this.service.setEtat(evenement));
	    }
	 
	 
	
	 
	 
	 @RequestMapping(method = RequestMethod.DELETE,value = "/deleteIncident/{id}")     
     public Map<String, Boolean> delete(@PathVariable String id) {
  return  service.delete(id);
    
    }
	 
	 @PutMapping( "/updateEtat/{id}")
	 public ResponseEntity < EvenementDetail > updateEtat(@PathVariable String id, @RequestBody EvenementDetail evenement) {
		 evenement.setId(id);
		   return ResponseEntity.ok().body(this.service.updateEtat(evenement));
	    }
	 
	 @RequestMapping(value = "/afficheIncidentParFamille",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    List<EvenementDetail> afficheIncidentParFamille(@RequestBody @Valid Famille nomFamille) {
	        return service.afficheIncidentParFamille(nomFamille);
	   }
	 @RequestMapping(method = RequestMethod.PUT,value = "/UpdateIncident")
	
	    EvenementDetail updateEvenementDetail(@RequestBody @Valid EvenementDetail actifEntry) {
	    	System.out.println("hiiii");
	        return service.updateEvenementDetail(actifEntry);
	    }
	 @RequestMapping(method = RequestMethod.GET,value = "/countAllIncident")
	    long countAllIncident() {
	        return service.countAllIncident();
	    }
}

