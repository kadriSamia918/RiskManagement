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

import com.telnet.project.Entities.Evenement;
import com.telnet.project.Entities.RisqueLog;
import com.telnet.project.ServiceImpl.RisqueLogImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class RisqueLogController {
	  private   RisqueLogImpl service;
	   
	   @Autowired
	   RisqueLogController(RisqueLogImpl service) {
	        this.service = service;
	    }
	   
	   @RequestMapping(method = RequestMethod.POST,value = "/SaveRisqueLog")
	   public Map<String, Boolean> saveRisqueLog(@RequestBody @Valid RisqueLog log) {
	        return service.saveLog(log);
	    }
	   @RequestMapping(value = "/afficherRisqueLog", method = RequestMethod.GET)
	   List<RisqueLog> findAll() {
			 
	        return service.findAll();
	        
	    }
	   @RequestMapping(value = "/afficherRisqueLog/{risqueId}", method = RequestMethod.GET)
	   List<RisqueLog> findLogByRisqueId(@PathVariable String risqueId) {
	          return service.findLogByRisqueId(risqueId);
	}
	   

}
