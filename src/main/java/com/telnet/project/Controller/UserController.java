package com.telnet.project.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.telnet.project.Entities.Role;
import com.telnet.project.Entities.User;
import com.telnet.project.ServiceImpl.UserServiceImpl;
import com.telnet.project.webSocket.Notification;

@Component
@RestController
@RequestMapping("/RiskManagementservice/api/pfe")
@CrossOrigin("*")
public class UserController {
	
	   private   UserServiceImpl service;
	   @Autowired
	   UserController(UserServiceImpl service) {
	        this.service = service;
	    }
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherUsers")
	    List<User> findAll() {
	  	        return service.findAll();
	        
	    }
	   @RequestMapping(method = RequestMethod.GET,value = "/getUsersCount")
	    long getUsersCount() {
	  	        return service.getUserCount();
	        
	    }
	   @RequestMapping(method = RequestMethod.GET,value = "/findNotSeenRequests")
	    List<User> findNotSeenRequests() {
	  	        return service.findNotSeen();
	        
	    }
	
	   @RequestMapping(method = RequestMethod.PUT,value = "/lastConnection/{username}")
	   Map<String, Boolean>lastConnection(@RequestBody @Valid Date lastconnection,@PathVariable  String username) {
	        return service.updateLastConnection(username, lastconnection);
	    }
	   @RequestMapping(method = RequestMethod.PUT,value = "/updateUser")
	   Map<String, Boolean>updateUser(@RequestBody  User user ) {
	        return service.updateUser(user);
	    }
	   
	   @RequestMapping(method = RequestMethod.PUT,value = "/updateUserRole")
	   Map<String, User>updateUserRole(@RequestBody  User user ) {
	        return service.updateUserRole(user);
	    }
	   @RequestMapping(method = RequestMethod.PUT,value = "/ActivateUser")
	   Map<String, Boolean>activateUser(@RequestBody  User user ) {
	        return service.confirmUser(user);
	    }
	   @RequestMapping(value = "/{username}", method = RequestMethod.GET)
	    public User getUserbyUsername(@PathVariable String username) {
	          return service.getUserbyUsername(username);
	}
	   @RequestMapping(value = "/image/{username}", method = RequestMethod.GET)
	    public Map<String, Boolean> findImage(@PathVariable String username) {
	          return service.findImage(username);
	}
	   @RequestMapping(method = RequestMethod.PUT,value = "/SetInactive")
	   Map<String, Boolean>setInactivateUser(@RequestBody  String id ) {
	        return service.setInactive(id);
	    }
	   
	    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteUser/{username}")     
	     public Map<String, Boolean> delete(@PathVariable String username) {
	  return  service.delete(username);
	    
	    }
	   }


