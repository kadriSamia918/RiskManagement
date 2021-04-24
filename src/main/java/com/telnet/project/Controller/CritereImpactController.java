package com.telnet.project.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.CritereImpact;
import com.telnet.project.Entities.CritereMenace;
import com.telnet.project.ServiceImpl.CritereImpactServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class CritereImpactController {
	
	@Autowired
	private   CritereImpactServiceImpl service;
	
	  @RequestMapping(method = RequestMethod.GET,value = "/afficherCritereImpact")
	    List<CritereImpact> findAll() {
	        return service.findAll();
	    }
	  @RequestMapping(method = RequestMethod.PUT,value = "/updateImpactMenace")
	   Map<String, Boolean>updateImpactMenace(@RequestBody CritereImpact impactMenace ) {
	        return service.update(impactMenace);
	    }
	

}
