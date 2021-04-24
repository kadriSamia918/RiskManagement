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

import com.telnet.project.Entities.ProbaRisk;
import com.telnet.project.Entities.ValorisationRisk;
import com.telnet.project.ServiceImpl.ValorisationRiskServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class ValorisationRiskController {
	
	private ValorisationRiskServiceImpl service;
	
	@Autowired
	ValorisationRiskController(ValorisationRiskServiceImpl service){
		this.service=service;
	}
	
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherValorisationRisk")
	    List<ValorisationRisk> findAll() {
	    	
	        return service.findAll();
	        
	    }
	   
	   @RequestMapping(method = RequestMethod.POST,value = "/createValorisationRiskList")
	   List<ValorisationRisk> createListValorisation(@RequestBody @Valid List<ValorisationRisk> valorisationRisk) {
	        return service.createListValorisation(valorisationRisk);
	    }
	   @RequestMapping(method = RequestMethod.POST,value = "/createValorisationRisk")
	   ValorisationRisk createValorisationRisk(@RequestBody @Valid ValorisationRisk valorisationRisk) {
	        return service.createProbaRisk(valorisationRisk);
	    }
	   @RequestMapping(method = RequestMethod.POST,value = "/updateValorisationRiskList")
	   List<ValorisationRisk> updateValorisationRisk(@RequestBody @Valid List<ValorisationRisk> probaList) {
	        return service.updateValorisationList(probaList);
	    }
	   @RequestMapping(value = "/getValorisationByImpact/{criterImpact}", method = RequestMethod.GET)
	   List<ValorisationRisk> getValorisationByImpact(@PathVariable String criterImpact) {
	          return service.getValorisationByImpact(criterImpact);
	}
	   
	   @RequestMapping(method = RequestMethod.PUT,value = "/updateValorisationByImpact/{vraissemblance}")
	   Map<String,List<ValorisationRisk>>updateValorisationByImpact(@PathVariable  String vraissemblance,@RequestBody  List<ValorisationRisk> probaList) {
	        return service.updateValorisationByImpact(vraissemblance, probaList);
	    }

}
