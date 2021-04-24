package com.telnet.project.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Action;
import com.telnet.project.Entities.CategorieIncident;
import com.telnet.project.Entities.Evenement;
import com.telnet.project.Entities.EvenementDetail;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.Personne;
import com.telnet.project.Entities.Risque;
import com.telnet.project.Entities.Statistique;
import com.telnet.project.Entities.TauxEvenement;
import com.telnet.project.Entities.User;
import com.telnet.project.Repository.EvenementRepository;
import com.telnet.project.ServiceImpl.EvenementServiceImp;
import com.telnet.project.ServiceImpl.TauxRisque;

@Component
@RestController
@RequestMapping("/api/pfe")
public class EvenementController {
	
	private EvenementServiceImp service;
	private EvenementRepository evenementRepository;
	
	 @RequestMapping(method = RequestMethod.POST,value = "/ajoutEvenement")
	   Map<String, Evenement> ajoutEvenement(@RequestBody @Valid Evenement EvenementEntry) {
	        return service.ajoutEvenement(EvenementEntry);
	    }
	
	  @RequestMapping(method = RequestMethod.GET,value = "/affichercountTauxEvenement")
	   TauxEvenement countTauxEvenement() {
		  
	        return service.countTauxEvenement();
	        
	    }
	  @RequestMapping(method = RequestMethod.GET,value = "/evenementParMois")
	   List<Statistique> EvenementParMois() {
	    	
	        return service.EvenementParMois();
	        
	    }
	   
	 @PutMapping( "/SetInactiveEvenement/{id}")
	 public ResponseEntity < Evenement > setInactivateEvenement(@PathVariable String id, @RequestBody Evenement evenement) {
		 evenement.setId(id);
		   return ResponseEntity.ok().body(this.service.setInactive(evenement));
	    }
	 
	 @RequestMapping(method = RequestMethod.GET,value = "/afficherEvenement")
	   List<Evenement> findAll() {
		 
	        return service.findAll();
	        
	    }
	 
	 @PutMapping( "/updateType/{id}")
	 public ResponseEntity < Evenement > updateEtat(@PathVariable String id, @RequestBody Evenement evenement) {
		 evenement.setId(id);
		 
		   return ResponseEntity.ok().body(this.service.updateType(evenement));
	    }
	 @PutMapping( "/updateType2/{id}")
	 public ResponseEntity < Evenement > updateEtat2(@PathVariable String id, @RequestBody Evenement evenement) {
		 evenement.setId(id);
		 
		   return ResponseEntity.ok().body(this.service.updateType2(evenement));
	    }
	 
	 @RequestMapping(method = RequestMethod.PUT,value = "/UpdateEvenement")
	   Map<String, Boolean>updateRisque(@RequestBody  Evenement evenement ) {
	        return service.updateEvenement(evenement);
	    }
	 
	 @RequestMapping(method = RequestMethod.POST,value = "/afficherEvenementParUser")
	    List<Evenement> findEvenementParDetecteur(@RequestBody @Valid String detecteur) {
	    	
	        return service.findEvenementByDetecteur(detecteur);	        
	    }
	 
	 @RequestMapping(method = RequestMethod.POST,value = "/afficherEvenementParCategory")
	    List<Evenement> findEvenementParCategory(@RequestBody @Valid CategorieIncident categorie) {
	    	
	        return service.findEvenementByCategory(categorie);	        
	    }
	
	 
	
	  @RequestMapping(method = RequestMethod.DELETE,value = "/deleteEvenement/{evenemnt}")     
	     public Map<String, Boolean> delete(@PathVariable String evenement) {
	  return  service.delete(evenement);
	    
	    }
	  @RequestMapping(method = RequestMethod.GET,value = "/countAllEvenement")
	    long countAllIncident() {
	        return service.countAllEvenement();
	    }

	public EvenementController(EvenementServiceImp service) {
	
		this.service = service;
	}}
