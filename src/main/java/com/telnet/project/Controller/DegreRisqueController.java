package com.telnet.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.DegreRisque;
import com.telnet.project.ServiceImpl.DegreRisqueServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class DegreRisqueController {
	
	private DegreRisqueServiceImpl service;
	
	@Autowired
	DegreRisqueController(DegreRisqueServiceImpl service){
		this.service=service;
	}
	
	 @RequestMapping(method = RequestMethod.GET,value = "/afficherDegreRisque")
	    List<DegreRisque> findAll() {
	    	
	        return service.findAll();
	 }
	    

}
