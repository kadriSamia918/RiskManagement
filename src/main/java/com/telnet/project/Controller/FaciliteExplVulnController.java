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

import com.telnet.project.Entities.Action;
import com.telnet.project.Entities.Critere_Securite;
import com.telnet.project.Entities.FaciliteExplVuln;
import com.telnet.project.ServiceImpl.Critere_SecuriteServiceImpl;
import com.telnet.project.ServiceImpl.FaciliteExplVulnImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class FaciliteExplVulnController {
	
	@Autowired
	private   FaciliteExplVulnImpl service;
	
	  @RequestMapping(method = RequestMethod.GET,value = "/afficherFaciliteExplVuln")
	    List<FaciliteExplVuln> findAll() {
	    	
	        return service.findAll();
	        
	    }
	  
	  @RequestMapping(method = RequestMethod.POST,value = "/ajoutFaciliteExplVuln")
	    FaciliteExplVuln ajout(@RequestBody @Valid FaciliteExplVuln faciliteExplVulnEntry ) {
	    	
	        return service.ajout(faciliteExplVulnEntry);
	        
	    }
	   
	    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteFaciliteExplVuln/{id}")     
	     public Map<String, Boolean> delete(@PathVariable String id) {
	  return  service.delete(id);
	    
	    
	    }
	    @RequestMapping(method = RequestMethod.PUT,value = "/updateFacilite")
		   Map<String, Boolean>updateFacilite(@RequestBody  FaciliteExplVuln facilite ) {
		        return service.update(facilite);
		    }

}
