package com.telnet.project.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.Risque;
import com.telnet.project.Entities.SousFamille;
import com.telnet.project.Entities.TypeMenace;
import com.telnet.project.ServiceImpl.TypeMenaceServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class TypeMenaceController {

	  private   TypeMenaceServiceImpl service;
	   
	   @Autowired
	   TypeMenaceController(TypeMenaceServiceImpl service) {
	        this.service = service;
}
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherTypeMenace")
	   List<TypeMenace> afficherTypeMenace() {
	        return service.findAll();
	    }
	   @RequestMapping(value = "/createTypeMenace",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	   TypeMenace ajoutTypeMenace(@RequestBody @Valid TypeMenace typeMenaceEntry) {
	        return service.createTypeMenace(typeMenaceEntry);
	    }
	   
	    @RequestMapping(method = RequestMethod.PUT,value = "/updateTypeMenace/{id}")  
	    @ResponseStatus(HttpStatus.CREATED)
	    TypeMenace update (@RequestBody @Valid TypeMenace TypeMenaceEntry)
	    	{
	    	return service.update(TypeMenaceEntry);
	    }
	    
	   @RequestMapping(method = RequestMethod.DELETE,value = "/deleteTypeMenace/{id}")     
	    void delete(@RequestBody @Valid TypeMenace TypeMenaceEntry) {
	    service.delete(TypeMenaceEntry);
	    
	    }
}