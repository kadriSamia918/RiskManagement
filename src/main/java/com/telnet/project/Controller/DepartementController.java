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
import com.telnet.project.Entities.Departement;
import com.telnet.project.ServiceImpl.CategorieIncidentServiceImpl;
import com.telnet.project.ServiceImpl.DepartementServiceImp;
import com.telnet.project.Services.DepartementService;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class DepartementController {

	@Autowired
	 private   DepartementServiceImp service;
	   
	   @Autowired
	   DepartementController(DepartementServiceImp service) {
	        this.service = service;
	    }
	 @RequestMapping(method = RequestMethod.GET,value = "/afficherDepartement")
	    List<Departement> findAll() {
	    	
	        return service.findAll();
	        
	    }
	 
	   @RequestMapping(value = "/createDepartement",method = RequestMethod.POST)
		  
	   Departement create(@RequestBody @Valid Departement dp) {
	        return service.createDepartement(dp);
	    }
	
}
