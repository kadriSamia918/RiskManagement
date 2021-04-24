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

import com.telnet.project.Entities.DegreCriticite;
import com.telnet.project.Entities.ProbaRisk;
import com.telnet.project.ServiceImpl.DegreCriticiteImpl;
import com.telnet.project.ServiceImpl.ProbaRiskImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class DegreCriticiteController {
	
private DegreCriticiteImpl service;
	
	@Autowired
	DegreCriticiteController(DegreCriticiteImpl service){
		this.service=service;
	}
	
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherDegreCriticite")
	    List<DegreCriticite> findAll() {
	    	
	        return service.findAll();
	        
	    }
	   
	   @RequestMapping(method = RequestMethod.POST,value = "/createDegreCriticite")
	   DegreCriticite createDegreCriticite(@RequestBody @Valid DegreCriticite degreCriticite) {
	        return service.createDegreCriticite(degreCriticite);
	    }

}
