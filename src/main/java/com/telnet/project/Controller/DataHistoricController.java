package com.telnet.project.Controller;

import java.util.List;

import javax.validation.Valid;

import org.javers.core.metamodel.object.CdoSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.Risque;
import com.telnet.project.ServiceImpl.DataHistoricService;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class DataHistoricController {
	  
	  private   DataHistoricService service;

	  @Autowired
	  DataHistoricController(DataHistoricService service) {
	        this.service = service;
	    }
	  
	  @RequestMapping(method = RequestMethod.GET,value = "/getChangesOnRisk}")
	  List<CdoSnapshot> getChangesOnRisk() {
	    	
	        return service.readFromHistorie();
	        
	    }
}
