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

import com.telnet.project.Entities.SourceMenace;
import com.telnet.project.Entities.TypeMenace;
import com.telnet.project.ServiceImpl.SourceMenaceServiceImpl;
import com.telnet.project.ServiceImpl.TypeMenaceServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class SourceMenaceController {
	  private   SourceMenaceServiceImpl service;
	  
	  @Autowired
	   SourceMenaceController(SourceMenaceServiceImpl service) {
	        this.service = service;
}
	   @RequestMapping(value = "/createSourceMenace",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	   SourceMenace ajoutSourceMenace(@RequestBody @Valid SourceMenace sourceMenaceEntry) {
	        return service.createSourceMenace(sourceMenaceEntry);
	    }
	   
	    @RequestMapping(method = RequestMethod.PUT,value = "/updateSourceMenace/{id}")  
	    @ResponseStatus(HttpStatus.CREATED)
	    SourceMenace update (@RequestBody @Valid SourceMenace SourceMenaceEntry)
	    	{
	    	return service.update(SourceMenaceEntry);
	    }
	    
	   @RequestMapping(method = RequestMethod.DELETE,value = "/deleteSourceMenace/{id}")     
	    void delete(@RequestBody @Valid SourceMenace sourceMenaceEntry) {
	    service.delete(sourceMenaceEntry);
	    
	    }
	   
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherSourceMenace")
	   List<SourceMenace> afficherSourceMenace() {
	        return service.findAll();
	    }
}
	  

