package com.telnet.project.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.CritereMenace;
import com.telnet.project.Entities.FaciliteExplVuln;
import com.telnet.project.ServiceImpl.CritereMenaceServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class CritereMenaceController {
	@Autowired
	private   CritereMenaceServiceImpl service;
	
	  @RequestMapping(method = RequestMethod.GET,value = "/afficherCritereMenace")
	    List<CritereMenace> findAll() {
	        return service.findAll();
	    }
	    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteCritereMenace/{id}")     
	     public Map<String, Boolean> delete(@PathVariable String id) {
	  return  service.delete(id);
	    
	    
	    }
	    @RequestMapping(method = RequestMethod.POST,value = "/ajoutCritereMenace")
	    CritereMenace ajout(@RequestBody @Valid CritereMenace critereMenaceEntry ) {
	    	
	        return service.ajout(critereMenaceEntry);
	        
	    }
	    
	    @RequestMapping(method = RequestMethod.PUT,value = "/updateCritereMenace")
		   Map<String, Boolean>updateCritereMenace(@RequestBody CritereMenace critereMenace ) {
		        return service.update(critereMenace);
		    }
}
