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

import com.telnet.project.Entities.Action;
import com.telnet.project.Entities.CategorieAction;
import com.telnet.project.ServiceImpl.CategorieActionServiceImpl;
@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class CategeorieActionController {
	@Autowired
	 private   CategorieActionServiceImpl service;
	   
	   @Autowired
	   CategeorieActionController(CategorieActionServiceImpl service) {
	        this.service = service;
	    }
	 @RequestMapping(method = RequestMethod.GET,value = "/afficherCategorie")
	    List<CategorieAction> findAll() {
	    	
	        return service.findAll();
	        
	    }
	 
	   @RequestMapping(value = "/createActionCategorie",method = RequestMethod.POST)
		  
	   CategorieAction create(@RequestBody @Valid CategorieAction action) {
	        return service.createActionCategorie(action);
	    }

}
