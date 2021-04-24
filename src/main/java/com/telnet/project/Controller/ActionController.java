
package com.telnet.project.Controller;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Action;
import com.telnet.project.Entities.CategorieAction;
import com.telnet.project.Entities.Evenement;
import com.telnet.project.Entities.Risque;
import com.telnet.project.Entities.Statistique;
import com.telnet.project.Entities.User;
import com.telnet.project.ServiceImpl.ActionServiceImpl;


@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class ActionController {
	
	private  ActionServiceImpl service;
	   
	   @Autowired
	   ActionController( ActionServiceImpl service) {
	        this.service = service;
	    }
	   @RequestMapping(value = "/createAction",method = RequestMethod.POST)
	  
	    Action create(@RequestBody @Valid Action action) {
	        return service.createAction(action);
	    }
	   @RequestMapping(method = RequestMethod.GET,value = "/efficaciteParMois")
	   List<Statistique> efficaciteParMois() {
	    	
	        return service.efficaciteParMois();
	        
	    }
	   
	   @RequestMapping(method = RequestMethod.GET,value = "/avancementActions")
	   List<Statistique> avancementActions() {
	    	
	        return service.avancementActions();
	        
	    }
	   
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherAction")
	    List<Action> findAll() {
	        return service.findAll();
	    }
	   @GetMapping("/action/{code}")
	    public ResponseEntity < Action > getActionByCode(@PathVariable String code) {
	        return ResponseEntity.ok().body(service.getActionByCode(code));
	    }
	   
	   @RequestMapping(method = RequestMethod.GET,value = "/afficherActionApprouve")
	    List<Action> findApprovedActions() {
	        return service.findAcceptedAction();
	    }
	   
	   @RequestMapping(method = RequestMethod.GET,value = "/countAllAction")
	    long countAllAction() {
	        return service.countAllActions();
	    }
	    @RequestMapping(method = RequestMethod.PUT,value = "/dG_Action/{codeAction}")
	    Action dG_Action(@RequestBody @Valid String dG,@PathVariable  String codeAction) {
	    	System.out.println("hiiii");
	        return service.dG_Action(codeAction, dG);
	    }
	    @RequestMapping(method = RequestMethod.PUT,value = "/decision/{id}")
		   Map<String, Boolean>dG_Decision(@RequestBody @Valid String decision,@PathVariable  String id) {
		        return service.dG_Decision(id, decision);
		    }
	    @RequestMapping(value = "/actionEnAttente/{decision}", method = RequestMethod.GET)
	    public List<Action>  getActionsEnAttente(@PathVariable String decision) {
	          return service. getActionsEnAttente(decision);
	}
	    
	 
	    
	    
	    
	    @RequestMapping(value = "/actionByCategorie/{categorie}", method = RequestMethod.GET)
	    public List<Action>  getActionsByCategorie(@PathVariable String categorie) {
	          return service. getActionsByCategorie(categorie);
	}
	    
	    
	    
	    @RequestMapping(method = RequestMethod.PUT,value = "/UpdateAction")
		   Map<String, Boolean>updateAction(@RequestBody  Action action ) {
		        return service.updateAction(action);
		    }
}