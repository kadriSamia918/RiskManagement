package com.telnet.project.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.telnet.project.Entities.Famille;

import com.telnet.project.ServiceImpl.FamilleServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class FamilleController {
		
	   private   FamilleServiceImpl service;
	   
	   @Autowired
	   FamilleController(FamilleServiceImpl service) {
	        this.service = service;
	    
	   }
	  
	    /* 
	    @RequestMapping(value = "/createFamilleActif",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    FamilleDTO create(@RequestBody @Valid FamilleDTO familleEntry) {
	        return service.createFamille(familleEntry);
	    }
	   */
	   
	    @RequestMapping(value = "/createFamilleActif",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    Famille create(@RequestBody @Valid Famille familleEntry) {
	        return service.createFamille(familleEntry);
	    }
	    @RequestMapping(method = RequestMethod.GET,value = "/afficherFamilleActif")
		//@PreAuthorize("hasRole('USER') or hasRole('USER2') ")
				List<Famille> findAll() {
	    	
	        return service.findAll();
	        
	    }
	   
	    @RequestMapping(method = RequestMethod.GET,value = "/afficherCountAllFamille")
	    Long findCountAllFamilles() {
	    	
	        return service.countAllFamilles();
	        
	    }
	 /*   @RequestMapping(method = RequestMethod.GET,value = "/affichersousFamilleActif/{nomFamille}")
	    List<Famille> findSousFamille((@PathVariable String nomFamille) {
	    	
	        return service.findAll();
	        
	    }*/
	    /*
	    @RequestMapping(method = RequestMethod.POST,value = "/afficherFamilleActifparCat")
	    List<Famille> findParCat(@RequestBody @Valid Category categoryEntry) {
	    	
	        return service.afficheParCat(categoryEntry);
	    
	    }
	    */
	    @RequestMapping(method = RequestMethod.POST,value = "/afficherFamilleParNomTree")
	    Famille findParFamilleTree(@RequestBody @Valid Famille famille) {
	        return service.afficherFamilleParNomTree(famille);
	    }
	
	    @RequestMapping(method = RequestMethod.POST,value = "/afficherFamilleParNom")
	    Famille findParFamille(@RequestBody @Valid Famille famille) {
	        return service.afficherFamilleParNom(famille);
	    }
	
	    @RequestMapping(method = RequestMethod.PUT,value = "/updateFamilleActif/{id}")  
	    @ResponseStatus(HttpStatus.CREATED)
	    Famille update (@RequestBody @Valid Famille familleEntry)
	    	{
	    	
	    	return service.update(familleEntry);
	    
	    	}
	    
	   @RequestMapping(method = RequestMethod.DELETE,value = "/deleteFamilletActif/{id}")     
	     public Map<String, Boolean> delete (@PathVariable String id) 
	   {
	   return service.delete(id);
	   
	   }
}


