package com.telnet.project.Controller;

import java.io.IOException;
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

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.Personne;
import com.telnet.project.ServiceImpl.ActifServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class ActifController {
	
	  private   ActifServiceImpl service;
	   
	   @Autowired
	   ActifController(ActifServiceImpl service) {
	        this.service = service;
	    }
	   
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherActif")
	    List<Actif> findAll() {
	    	
	        return service.findAll();
	        
	    }

	   @RequestMapping(method = RequestMethod.GET,value = "/afficherActifParProprietaire")
	    List<Actif> findActifPArProprietaire(@RequestBody @Valid Personne proprietaire) {
	    	
	        return service.findActifByProprietaire(proprietaire);
	        
	    }
	
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherCountAllActif")
	    Long countAllActif() {
	    	
	        return service.countAllActif();
	        
	    }
	   
	   @RequestMapping(method = RequestMethod.POST,value = "/afficherActifParFamille")
	    List<Actif> findParFamille(@RequestBody @Valid Famille famille) {
	        return service.afficherActifParFamille(famille);
	    }
	    @RequestMapping(method = RequestMethod.POST,value = "/ajoutActifSousFamille/{id_Famille}/{nomSousFamille}")
	    Actif ajoutActifTree(@RequestBody @Valid Actif actifEntry,@PathVariable String id_Famille,@PathVariable String nomSousFamille) {
	        return service.ajoutActifTree(actifEntry,id_Famille,nomSousFamille);
	    }
	    
	    @RequestMapping(method = RequestMethod.POST,value = "/ajoutActif/{id_Famille}")
	    Actif ajoutActif(@RequestBody @Valid Actif actifEntry,@PathVariable String id_Famille) {
	        return service.ajoutActif(actifEntry,id_Famille);
	    }
	    
	    @RequestMapping(method = RequestMethod.PUT,value = "/updateActif")
	    Actif updateActif(@RequestBody @Valid Actif actifEntry) {
	    	System.out.println("hiiii");
	        return service.updateActif(actifEntry);
	    }
	    
	    @RequestMapping(method = RequestMethod.PUT,value = "/updateProprietaireActifList/{id}")
	    List<Actif> updateActifeProprietaire(@RequestBody @Valid List<Actif> actifEntrys,@PathVariable
	   	     String id) {
	        return service.updateActifProprietaireList(actifEntrys,id);
	    }
	    
	    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteActif/{id}")     
	     public Map<String, Boolean> delete(@PathVariable String id) {
	  return  service.delete(id);
	    
	    }
	   
}
