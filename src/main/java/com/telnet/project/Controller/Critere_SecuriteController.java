package com.telnet.project.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.Critere_Securite;
import com.telnet.project.Entities.Echelle_Criticite;
import com.telnet.project.ServiceImpl.Critere_SecuriteServiceImpl;


@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public final class Critere_SecuriteController {
	@Autowired
	private   Critere_SecuriteServiceImpl service;
	
	  @RequestMapping(method = RequestMethod.GET,value = "/afficherCritere")
	    List<Critere_Securite> findAll() {
	    	
	        return service.findAll();
	        
	    }
	  
	    @RequestMapping(method = RequestMethod.GET,value = "/afficherCritereDisponibilite")
	    List<Echelle_Criticite> afficherCritereDisponibilite() {
	    	
	        return service.afficherCritereDisponibilite();
	        
	    }
	    
	    @RequestMapping(method = RequestMethod.GET,value = "/afficherCritereConfidentialite")
	    List<Echelle_Criticite> afficherCritereConfidentialite() {
	    	
	        return service.afficherCritereConfidentialite();
	        
	    }
	    @RequestMapping(method = RequestMethod.GET,value = "/afficherCritereIntegrite")
	    List<Echelle_Criticite> afficherCritereIntegrite() {
	    	
	        return service.afficherCritereIntegrite();
	        
	    }
	
    @RequestMapping(value = "/createCritere_SecuriteConfidentialite",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    Critere_Securite createCritere_Securite() {
        return service.createCritere_SecuriteConfidentialite();
    }
    
    @RequestMapping(value = "/createCritere_SecuriteIntegrite",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    Critere_Securite createCritere_SecuriteIntegrite() {
        return service.createCritere_SecuriteIntegrite();
    }
    
    @RequestMapping(value = "/createCritere_SecuriteDisponibilite",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    Critere_Securite createCritere_SecuriteDisponibilite() {
        return service.createCritere_SecuriteDisponibilite();
    }
    
    @RequestMapping(value = "/updateCritere_SecuriteConfidentialite",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    List<Echelle_Criticite> updateCritere_SecuriteConfidentialite(@RequestBody @Valid List<Echelle_Criticite> echelle_CriticiteEntry) {
        return service.updateCritere_SecuriteConfidentialite(echelle_CriticiteEntry);
    }
    
    @RequestMapping(value = "/ajoutCritere_SecuriteDisponibilite",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> ajoutCritere_SecuriteDisponibilite(@RequestBody @Valid Echelle_Criticite echelle_CriticiteEntry) {
        return service.ajoutCritere_SecuriteDisponibilite(echelle_CriticiteEntry);
    }
    
    @RequestMapping(value = "/ajoutCritere_SecuriteConfidentialite",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> ajoutCritere_SecuriteConfidentialite(@RequestBody @Valid Echelle_Criticite echelle_CriticiteEntry) {
        return service.ajoutCritere_SecuriteConfidentialite(echelle_CriticiteEntry);
    }
    @RequestMapping(value = "/ajoutCritere_SecuriteIntegrite",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> ajoutCritere_SecuriteIntegrite(@RequestBody @Valid Echelle_Criticite echelle_CriticiteEntry) {
        return service.ajoutCritere_SecuriteIntegrite(echelle_CriticiteEntry);
    }
    @RequestMapping(value = "/updateCritere_SecuriteIntegrite",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    List<Echelle_Criticite> updateCritere_SecuriteIntegrite(@RequestBody @Valid List<Echelle_Criticite> echelle_CriticiteEntry) {
        return service.updateCritere_SecuriteIntegrite(echelle_CriticiteEntry);
    }
    
 
    
}
