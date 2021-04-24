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

import com.telnet.project.Entities.DegreCriticite;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.ProbaRisk;
import com.telnet.project.Entities.RisqueLog;
import com.telnet.project.ServiceImpl.ProbaRiskImpl;
import com.telnet.project.webSocket.Notification;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class ProbaRiskController {
	
	private ProbaRiskImpl service;
	
	@Autowired
	ProbaRiskController(ProbaRiskImpl service){
		this.service=service;
	}
	
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherProbaRisk")
	    List<ProbaRisk> findAll() {
	    	
	        return service.findAll();
	        
	    }
	   @RequestMapping(value = "/getProbaByVraissemblance/{criterMenace}", method = RequestMethod.GET)
	   List<ProbaRisk> getProbaByVraissemblance(@PathVariable String criterMenace) {
	          return service.getProbaByVraissemblance(criterMenace);
	}
	   @RequestMapping(value = "/getProbaByFaciliteVul/{faciliteVul}", method = RequestMethod.GET)
	   List<ProbaRisk> getProbaByFaciliteVul(@PathVariable String faciliteVul) {
	          return service.getProbaByFaciliteVul(faciliteVul);
	}
	   @RequestMapping(method = RequestMethod.POST,value = "/createProbaRisk")
	   ProbaRisk createProbaRisk(@RequestBody @Valid ProbaRisk probaRisk) {
	        return service.createProbaRisk(probaRisk);
	    }
	   
	   @RequestMapping(method = RequestMethod.POST,value = "/updateProbaRiskList")
	   List<ProbaRisk> updateProbaRisk(@RequestBody @Valid List<ProbaRisk> probaList) {
	        return service.updateProbaRiskList(probaList);
	    }
	   
	   @RequestMapping(method = RequestMethod.POST,value = "/ajoutProbaRiskList")
	   List<ProbaRisk> ajoutProbaRisk(@RequestBody @Valid List<ProbaRisk> probaRiskList) {
	        return service.ajoutProbaRiskList(probaRiskList);
	    }
	   
	   @RequestMapping(method = RequestMethod.PUT,value = "/updateProbaByFaciliteVul/{faciliteVul}")
	   Map<String, List<ProbaRisk>>updateProbaByfacilte(@PathVariable  String faciliteVul,@RequestBody  List<ProbaRisk> probaList) {
	        return service.updateProbaByfacilte(faciliteVul, probaList);
	    }
	   
	   @RequestMapping(method = RequestMethod.PUT,value = "/updateProbaByVraissemblance/{vraissemblance}")
	   Map<String, List<ProbaRisk>>updateProbaByVraissemblance(@PathVariable  String vraissemblance,@RequestBody  List<ProbaRisk> probaList) {
	        return service.updateProbaByVraissemblance(vraissemblance, probaList);
	    }
}

