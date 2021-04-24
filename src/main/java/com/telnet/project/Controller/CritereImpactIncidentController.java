package com.telnet.project.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.CritereImpactIncident;
import com.telnet.project.Entities.Evenement;
import com.telnet.project.ServiceImpl.CritereImpactIncidentServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class CritereImpactIncidentController {
	
	@Autowired
	private   CritereImpactIncidentServiceImpl service;
	
	  @RequestMapping(method = RequestMethod.GET,value = "/afficherCritereImpactIncident")
	    List<CritereImpactIncident> findAll() {
	        return service.findAll();
	    }
	  @RequestMapping(method = RequestMethod.PUT,value = "/updateImpactIncident")
	   Map<String, Boolean>updateImpactIncident(@RequestBody CritereImpactIncident impactIncident ) {
	        return service.update(impactIncident);
	    }
	  
	  @RequestMapping(method = RequestMethod.POST,value = "/ajoutCritereImpactIncident")
	   Map<String, CritereImpactIncident> ajoutCritere(@RequestBody @Valid CritereImpactIncident EvenementEntry) {
	        return service.ajoutCritere(EvenementEntry);
	    }
	  @RequestMapping(method = RequestMethod.DELETE,value = "/deleteCritere/{id}")     
	     public Map<String, Boolean> delete(@PathVariable String id) {
	  return  service.delete(id);
	    
	    }

}