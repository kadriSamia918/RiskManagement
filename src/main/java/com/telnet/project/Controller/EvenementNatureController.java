package com.telnet.project.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.Evenement;
import com.telnet.project.Entities.EvenementNature;

import com.telnet.project.ServiceImpl.EvenementNatureServiceImp;
@Component
@RestController
@RequestMapping("/api/pfe")
public class EvenementNatureController {
	
	@Autowired
	private EvenementNatureServiceImp serviceNature;
	 @RequestMapping(method = RequestMethod.POST,value = "/ajoutNatureEvenement")
	   Map<String, EvenementNature> ajoutNatureEvenement(@RequestBody @Valid EvenementNature EvenementEntry) {
	        return serviceNature.ajoutNatureEvenement(EvenementEntry);
	    }
	 
	 
	 
	 @RequestMapping(method = RequestMethod.GET,value = "/afficherNatureEvenement")
	   List<EvenementNature> findAll() {
		 
	        return serviceNature.findAll();
	        
	    }
}
