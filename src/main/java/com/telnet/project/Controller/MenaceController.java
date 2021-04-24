package com.telnet.project.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.Menace;
import com.telnet.project.ServiceImpl.MenaceServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class MenaceController {
	
	private   MenaceServiceImpl service;
	   
	   @Autowired
	   MenaceController(MenaceServiceImpl service) {
	        this.service = service;
	    }
	   @RequestMapping(value = "/createMenace",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	   Map<String, Menace> create(@RequestBody @Valid Menace menaceEntry) {
	        return service.createMenace(menaceEntry);
	    }
	   
	   @RequestMapping(value = "/afficheMenaceParFamille",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    List<Menace> afficheMenaceParFamille(@RequestBody @Valid Famille nomFamille) {
	        return service.afficheMenaceParFamille(nomFamille);
	   }
	    @RequestMapping(method = RequestMethod.GET,value = "/afficherMenace")
	    List<Menace> findAll() {
	        return service.findAll();
	    }
	    
	    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteMenace/{id}")     
	     public Map<String, Boolean> delete(@PathVariable String id) {
	  return  service.delete(id);
	    	   

}}
