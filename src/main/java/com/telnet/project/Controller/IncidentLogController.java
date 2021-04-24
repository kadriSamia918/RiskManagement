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

import com.telnet.project.Entities.EvenementDetail;
import com.telnet.project.Entities.EvenementLog;
import com.telnet.project.Entities.IncidentLog;
import com.telnet.project.Entities.RisqueLog;
import com.telnet.project.ServiceImpl.EvenementLogImpl;
import com.telnet.project.ServiceImpl.IncidentLogImp;
@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")

public class IncidentLogController {


		private  IncidentLogImp service;
		   
		   @Autowired
		   IncidentLogController(IncidentLogImp service) {
		        this.service = service;
		    }
		   
		   @RequestMapping(method = RequestMethod.POST,value = "/SaveIncidentLog")
		   public Map<String, IncidentLog> saveIncidentLog(@RequestBody @Valid IncidentLog log) {
		        return service.saveLog(log);
		    }
	
		   @RequestMapping(value = "/afficherIncidentLog/{Id}", method = RequestMethod.GET)
		   List<IncidentLog> findLogByIncidentId(@PathVariable String Id) {
		          return service.findLogByIncidentId(Id);
		}
		  
		   
	
	
	
	
	
	
	
}
