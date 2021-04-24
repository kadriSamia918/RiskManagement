package com.telnet.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.Personne;
import com.telnet.project.ServiceImpl.PersonneServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class PersonneController {
	
	  private   PersonneServiceImpl service;
	   
	   @Autowired
	   PersonneController(PersonneServiceImpl service) {
	        this.service = service;
	    }
	   
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherPersonne")
	    List<Personne> findAll() {
	    	
	        return service.findAll();
	        
	    }
	   @GetMapping("/Personne/{id}")
	    public Personne getProductById(@PathVariable String id) {
	        return service.findPersonneById(id);
	    }


}
