package com.telnet.project.Controller;

import java.util.Date;
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

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.User;
import com.telnet.project.ServiceImpl.ActifServiceImpl;
import com.telnet.project.ServiceImpl.NotifServiceImpl;
import com.telnet.project.webSocket.Notification;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class NotifController {
	 private   NotifServiceImpl service;
	   
	   @Autowired
	   NotifController(NotifServiceImpl service) {
	        this.service = service;
	    }
	   
	   @RequestMapping(method = RequestMethod.POST,value = "/SaveNotification")
	    Notification notificationSave(@RequestBody @Valid Notification notif) {
	        return service.saveNotif(notif);
	    }
	   @RequestMapping(method = RequestMethod.POST,value = "/SendMailAfterRegistration")
	    Notification SendMailAfterRegistration(@RequestBody @Valid Notification notif) {
	        return service.SendMailAfterRegistration(notif);
	    }
	   @RequestMapping(value = "/notificationNumber/{username}", method = RequestMethod.GET)
	    public int getNotificationsbyRecieverNumber(@PathVariable String username) {
	          return service.getNotificationsNotSeenbyReciever(username);
	}
	   @RequestMapping(value = "/notification/{username}", method = RequestMethod.GET)
	    public List<Notification> getNotificationsbyReciever(@PathVariable String username) {
	          return service.getNotificationsbyReciever(username);
	}
	   @RequestMapping(method = RequestMethod.PUT,value = "/setNotificationIsSeen/{id}")
	   Map<String, Boolean>setNotificationIsSeen(@PathVariable  String id,@RequestBody  Notification seen) {
	        return service.setNotificationIsSeen(id);
	    }

}
