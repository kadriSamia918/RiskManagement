package com.telnet.project.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.ServiceImpl.MailServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class MailController {

	  private   MailServiceImpl service;
	   
	   @Autowired
	   MailController(MailServiceImpl service) {
	        this.service = service;
	    }
	
	@PostMapping(value = "/sendmail/{idFrom}/{idTo}")
    public void sendmail(@PathVariable String idFrom,@PathVariable String idTo,@RequestBody String text) {
      try {
    	  service.SendNotification(idFrom,idTo,text);
      }
      catch (MailException e) {
    	//  logger.info("Error sending mail" + e.getMessage());
      }

       

        
    }
 @PostMapping(value = "/sendmail/cc/{idFrom}/{idTo}")
    public void sendmailwithCC(@PathVariable String idFrom,@PathVariable String idTo,@RequestBody String text) {
      try {
    	 service.SendNotificationWithCC(idFrom, idTo ,text);;
      }
      catch (MailException e) {
    	//  logger.info("Error sending mail" + e.getMessage());
      }

       
    }
}
