package com.telnet.project.Controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.telnet.project.Entities.Critere_Securite;
import com.telnet.project.Entities.Echelle_Criticite;
import com.telnet.project.Repository.Critere_SecuriteRepository;
import com.telnet.project.ServiceImpl.Echelle_CriticiteServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class Echelle_CriticiteController {
	
	private   Echelle_CriticiteServiceImpl service;
	 @Autowired
	    private  Critere_SecuriteRepository critere_SecuriteRepository;  
	   @Autowired
	   Echelle_CriticiteController( Echelle_CriticiteServiceImpl service) {
	        this.service = service;
	    }
	  
	
	 
	    @RequestMapping(value = "/createEchelle_Criticite",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    Critere_Securite create(@RequestBody @Valid Critere_Securite critere_SecuriteEntry) {
	        return critere_SecuriteRepository.save(critere_SecuriteEntry);
	    }
	    
	 /*   @RequestMapping(method = RequestMethod.POST,value = "/afficherNiveauActifConfidentialite")
	    Echelle_Criticite afficherNiveauActifConfidentialite(@RequestBody @Valid Actif actif) {
	        return service.afficherNiveauActifConfidentialite(actif);
	    }
	    
	    @RequestMapping(method = RequestMethod.POST,value = "/afficherNiveauActifDisponibilite")
	    Echelle_Criticite afficherNiveauActifDisponibilite(@RequestBody @Valid Actif actif) {
	        return service.afficherNiveauActifDisponibilite(actif);
	    }
	    
	    @RequestMapping(method = RequestMethod.POST,value = "/afficherNiveauActifIntegrite")
	    Echelle_Criticite afficherNiveauActifIntegrite(@RequestBody @Valid Actif actif) {
	        return service.afficherNiveauActifIntegrite(actif);
	    }
	   */
	    @RequestMapping(method = RequestMethod.PUT,value = "/updateEchelle/{id}")  
	    @ResponseStatus(HttpStatus.CREATED)
	    Echelle_Criticite update (@RequestBody @Valid Echelle_Criticite echelleCriticiteEntry)
	    	{
	    	return service.updateEchelle(echelleCriticiteEntry);
	    }
	    
	    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteEchelleCriticite/{id}")     
	     public Map<String, Boolean> delete(@PathVariable String id) {
	  return  service.delete(id);
	    
	    
	    }
	    
	   /*
	    @RequestMapping(method = RequestMethod.GET,value = "/afficherAttributActif")
	    List<AttributActifDTO> findAll() {
	        return service.findAll();
	    }
	    
	
	
}

*/
}
