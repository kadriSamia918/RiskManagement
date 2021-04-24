package com.telnet.project.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.DTO.AttributActifDTO;
import com.telnet.project.Entities.AttributActif;
import com.telnet.project.Entities.Famille;
import com.telnet.project.ServiceImpl.AttributActifServiceImpl;



@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class AttributActifController {
	
	  
	   private   AttributActifServiceImpl service;
	   
	   @Autowired
	   AttributActifController( AttributActifServiceImpl service) {
	        this.service = service;
	    }
	  
	
	    @RequestMapping(value = "/createAttributActif",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    AttributActifDTO create(@RequestBody @Valid AttributActifDTO attributActifEntry) {
	        return service.createAttribut(attributActifEntry);
	    }
	    @RequestMapping(value = "/createAttributActifAdvanced",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    AttributActif createAttrinutActifAdvanced(@RequestBody @Valid AttributActif attributActifEntry) {
	        return service.createAttributActif(attributActifEntry);
	    }
	   
	    @RequestMapping(method = RequestMethod.GET,value = "/afficherAttributActif")
	    List<AttributActifDTO> findAll() {
	        return service.findAll();
	    }
	    @RequestMapping(method = RequestMethod.POST,value = "/afficherAttributParFamille")
	    List<AttributActif> findParFamille(@RequestBody @Valid Famille famille) {
	        return service.afficherParFamille(famille);
	    }
	    @RequestMapping(method = RequestMethod.PUT,value = "/updateAttributActifFamille")  
	    @ResponseStatus(HttpStatus.CREATED)
	    	Famille updateAttributFamille (@RequestBody @Valid Famille familleEntry)
	    	{
	    	return service.updateAttributFamille(familleEntry);
	        }
	    
	    @RequestMapping(method = RequestMethod.PUT,value = "/updateAttributActif/{id}")  
	    @ResponseStatus(HttpStatus.CREATED)
	    	AttributActif update (@RequestBody @Valid AttributActif attributActifEntry)
	    	{
	    	return service.update(attributActifEntry);
	    }
	    
	     @PutMapping("/updateAttribute/{nom}")
	     public List< AttributActif> updateAttribute(  @PathVariable
	     String nom,@Valid @RequestBody AttributActif attributEntry )
	     {
	    	 
	    	 return service.updateAttribute(nom,attributEntry );
	     }
	     @RequestMapping(method = RequestMethod.DELETE,value = "/deleteAttributActif/{id}")     
	     public Map<String, Boolean> delete(@PathVariable String id) {
	  return  service.delete(id);
	    
	    
	    }
	     
	     @PutMapping("/ajoutAttribut/{nom}")
	     public AttributActif ajoutAttribut(  @PathVariable
	     String nom,@Valid @RequestBody AttributActif attributEntry )
	     {
	    	 
	    	 return service.ajoutAttribut(nom,attributEntry );
	     }
	  
}


