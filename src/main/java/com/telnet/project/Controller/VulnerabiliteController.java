package com.telnet.project.Controller;

import java.util.List;
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

import com.telnet.project.DTO.AttributActifDTO;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.Menace;
import com.telnet.project.Entities.Vulnerabilite;
import com.telnet.project.ServiceImpl.VulnerabiliteServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class VulnerabiliteController {
	
	private   VulnerabiliteServiceImpl service;
	   
	   @Autowired
	   VulnerabiliteController(VulnerabiliteServiceImpl service) {
	        this.service = service;
	    }
	   
	    @RequestMapping(value = "/createVulnerabilite",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    Vulnerabilite create(@RequestBody @Valid Vulnerabilite vulnerabiliteEntry) {
	        return service.createVulnerabilite(vulnerabiliteEntry);
	    }
	   
	   
	    @RequestMapping(method = RequestMethod.GET,value = "/afficherVulnerabilite")
	    List<Vulnerabilite> findAll() {
	        return service.findAll();
	    }
	    
	    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteVulnerabilite/{id}")     
	     public Map<String, Boolean> delete(@PathVariable String id) {
	  return  service.delete(id);
	    
	    }
	    @RequestMapping(value = "/afficheVulnerabiliteParFamille",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    List<Vulnerabilite> afficheVulnerabiliteParFamille(@RequestBody @Valid Famille nomFamille) {
	        return service.affichVulnerabiliteParFamille(nomFamille);
	   }

}
