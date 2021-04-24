package com.telnet.project.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.Risque;
import com.telnet.project.Entities.Role;
import com.telnet.project.Entities.TypeMenace;
import com.telnet.project.ServiceImpl.RolesServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class RoleController {
	
	  private   RolesServiceImpl service;
	   
	   @Autowired
	   RoleController(RolesServiceImpl service) {
	        this.service = service;
	    }
	   
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherRoles")
	    List<Role> findAll() {
	    	
	        return service.findAll();
	        
	    }
	   @RequestMapping(value = "/createRole",method = RequestMethod.POST)
	   @ResponseStatus(HttpStatus.CREATED)
	   Map<String, Role> ajoutRole(@RequestBody @Valid Role role) {
	        return service.save(role);
	    }
	   @RequestMapping(method = RequestMethod.DELETE,value = "/deleteRole/{id}")     
	     public Map<String, Boolean> delete(@PathVariable String id) {
	  return  service.delete(id);
	    
	    }
	   
	   @RequestMapping(method = RequestMethod.PUT,value = "/UpdateRole")
	   Map<String, Boolean>updateRole(@RequestBody  Role role ) {
	        return service.update(role);
	    }
	   }