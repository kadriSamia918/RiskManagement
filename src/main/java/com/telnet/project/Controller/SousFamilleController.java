package com.telnet.project.Controller;

import java.util.List;


import javax.validation.Valid;
import javax.websocket.server.PathParam;

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

import com.telnet.project.DTO.SousFamilleDTO;
import com.telnet.project.Entities.SousFamille;
import com.telnet.project.ServiceImpl.SousFamilleServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class SousFamilleController {
   private   SousFamilleServiceImpl service;
	   
	   @Autowired
	   SousFamilleController(SousFamilleServiceImpl service) {
	        this.service = service;
	    }
	  
	
	    @RequestMapping(value = "/createSousFamilleActif",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    SousFamille ajoutSousFamille(@RequestBody @Valid SousFamille familleEntry) {
	        return service.ajoutSousFamille(familleEntry);
	    }
	    @RequestMapping(method = RequestMethod.POST,value = "/afficherSousFamilleActif/{sousFamille}")
	    SousFamille afficherSFparNom (@RequestBody @Valid SousFamille familleEntry,@PathVariable String sousFamille) {
	        return service.afficherSFparNom(sousFamille);
	    }
	   
	    @RequestMapping(method = RequestMethod.GET,value = "/afficherSousFamilleActif")
	    List<SousFamilleDTO> findAll() {
	        return service.findAll();
	    }
	    
	    @RequestMapping(method = RequestMethod.PUT,value = "/updateSousFamilleActif/{id}")  
	    @ResponseStatus(HttpStatus.CREATED)
	    SousFamille update (@RequestBody @Valid SousFamille familleEntry)
	    	{
	    	return service.update(familleEntry);
	    }
	    
	   @RequestMapping(method = RequestMethod.DELETE,value = "/deleteSousFamilletActif/{id}")     
	    void delete(@RequestBody @Valid SousFamille familleEntry) {
	    service.delete(familleEntry);
	    
	    }
}


