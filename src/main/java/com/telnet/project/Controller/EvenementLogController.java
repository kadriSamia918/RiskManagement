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

import com.telnet.project.Entities.EvenementLog;
import com.telnet.project.Entities.RisqueLog;
import com.telnet.project.ServiceImpl.EvenementLogImpl;
import com.telnet.project.ServiceImpl.RisqueLogImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class EvenementLogController {
	
	
	
	private  EvenementLogImpl service;
	   
	   @Autowired
	   EvenementLogController(EvenementLogImpl service) {
	        this.service = service;
	    }
	   
	   @RequestMapping(method = RequestMethod.POST,value = "/SaveEvenementLog")
	   public Map<String, Boolean> saveEvenementLog(@RequestBody @Valid EvenementLog log) {
	        return service.saveLog(log);
	    }
	   @RequestMapping(value = "/afficherEvenementLog/{evenementId}", method = RequestMethod.GET)
	   List<EvenementLog> findLogByEvenementId(@PathVariable String evenementId) {
	          return service.findLogByEvenementId(evenementId);
	}

}
