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

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.Menace;
import com.telnet.project.Entities.Risque;
import com.telnet.project.Entities.RisqueLog;
import com.telnet.project.Entities.Statistique;
import com.telnet.project.Entities.User;
import com.telnet.project.ServiceImpl.RisqueServiceImpl;
import com.telnet.project.ServiceImpl.TauxRisque;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class RisqueController {
	
	
	  private   RisqueServiceImpl service;
	   
	   @Autowired
	   RisqueController(RisqueServiceImpl service)
	   {
	        this.service = service;
	   }
	   
	   @RequestMapping(method = RequestMethod.POST,value = "/ajoutRisque")
	   Map<String, Risque> ajoutRisque(@RequestBody @Valid Risque risqueEntry) {
	        return service.ajoutRisque(risqueEntry);
	    }
	   @RequestMapping(value = "/afficheRisqueParFamille",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    List<Risque> afficheRisqueParFamille(@RequestBody @Valid Famille nomFamille) {
	        return service.afficheRisqueParFamille(nomFamille);
	   }
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherRisque")
	   List<Risque> afficherRisque() {
	        return service.findAll();
	    }
	   
	   
	   
	   @RequestMapping(method = RequestMethod.POST,value = "/afficherRisqueByActif")
	   List<Risque> afficherRisqueByActif(@RequestBody @Valid Actif actifs) {
	        return service.findRisqueByActif(actifs);
	    }
	   
	   
	   
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherCountAllRisques")
	    Long findCountAllRisques() {
	    	
	        return service.countAllRisques();
	        
	    }
	   @RequestMapping(method = RequestMethod.POST,value = "/countRriquesParFamilles")
	    int countRriquesParFamilles(@RequestBody @Valid Famille nomFamille) {
	    	
	        return service.countRisqueParFamille(nomFamille);
	        
	    }
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherCountRisquesResiduels")
	    Long countRisquesResiduels() {
	    	
	        return service.countRisquesResiduels();
	        
	    }
	   @RequestMapping(method = RequestMethod.PUT,value = "/UpdateRisque")
	   Map<String, Boolean>updateRisque(@RequestBody  Risque risque ) {
	        return service.updateRisque(risque);
	    }
	   
	   @RequestMapping(method = RequestMethod.GET,value = "/affichercountTauxRisque")
	   TauxRisque countTauxRisque() {
		  
	        return service.countTauxRisque();
	        
	    }
	   
	   @RequestMapping(method = RequestMethod.GET,value = "/TauxRisqueParSourceMenace")
	   List<Statistique> findRisqParSourceMenaceQuerry() {
		  
	        return service.findRisqParSourceMenace();
	        
	    }
	   @RequestMapping(value = "/getRisqueByCode/{code}", method = RequestMethod.GET)
	  Risque findLogByRisqueId(@PathVariable String code) {
	          return service.getRisqueById(code);
	}
}
