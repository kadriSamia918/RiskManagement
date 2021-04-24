package com.telnet.project.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.CategorieIncident;
import com.telnet.project.ServiceImpl.CategorieIncidentServiceImpl;
@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")

public class CategorieIncidentController {
	
		@Autowired
		 private   CategorieIncidentServiceImpl service;
		   
		   @Autowired
		   CategorieIncidentController(CategorieIncidentServiceImpl service) {
		        this.service = service;
		    }
		 @RequestMapping(method = RequestMethod.GET,value = "/afficherCategorieIncident")
		    List<CategorieIncident> findAll() {
		    	
		        return service.findAll();
		        
		    }
		 
		   @RequestMapping(value = "/createIncidentCategorie",method = RequestMethod.POST)
			  
		   CategorieIncident create(@RequestBody @Valid CategorieIncident action) {
		        return service.createIncidentCategorie(action);
		    }
}