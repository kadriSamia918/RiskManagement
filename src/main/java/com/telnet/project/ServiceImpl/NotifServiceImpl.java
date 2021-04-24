package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Action;
import com.telnet.project.Entities.User;
import com.telnet.project.Repository.NotificationRepository;
import com.telnet.project.Repository.UserRepository;
import com.telnet.project.Services.NotifService;
import com.telnet.project.webSocket.Notification;

@Service
public class NotifServiceImpl implements NotifService {
	
	 @Autowired
	 MongoTemplate mongoTemplate; 
	 
	 @Autowired
	 NotificationRepository notificationRepository;
	 
	 @Autowired
	 UserRepository userRepository;
	 
		@Autowired
		private MailServiceImpl mailService;

	@Override
	public List<Notification> findAll() {
		return notificationRepository.findAll();
	}

	public Notification SendMailAfterRegistration(Notification notif) {
		Notification saved = new Notification();
		saved.setMessage(notif.getMessage());
		saved.setTransactionId(notif.getTransactionId());
		User reciever=userRepository.findUserByUsername(notif.getRecieverId());
		saved.setRecieverId(reciever.getId());
	User sender=userRepository.findUserByUsername(notif.getSenderId());
		saved.setSenderId(sender.getId());
		saved.setSeen(notif.getSeen());
		mailService.SendEmailAfterRegistrationWithCC(notif.getRecieverId(), notif.getRecieverId());
		return notificationRepository.save(saved);
	}	
public Notification saveNotif(Notification notif) {
	Notification saved = new Notification();
	saved.setMessage(notif.getMessage());
	saved.setTransactionId(notif.getTransactionId());
	User reciever=userRepository.findUserByUsername(notif.getRecieverId());
	saved.setRecieverId(reciever.getUsername());
	User sender=userRepository.findUserByUsername(notif.getSenderId());
	saved.setSenderId(sender.getUsername());
	saved.setSeen(notif.getSeen());
	mailService.SendNotificationWithCC(sender.getUsername(), reciever.getUsername(),notif.getMessage());
	return notificationRepository.save(saved);
}
public List<Notification>getNotificationsbyReciever(String username){
	User user=userRepository.findUserByUsername(username);
	List<Notification> sendList= new ArrayList<Notification>();
	List<Notification> list= notificationRepository.findAllByRecieverId(user.getId());
	for(Notification notif:list) {
		if(notif.getSeen()== false) {
			sendList.add(notif);
		}
	}
	return(sendList);
}
public int getNotificationsNotSeenbyReciever(String username){
	User user=userRepository.findUserByUsername(username);
	List<Notification> sendList= new ArrayList<Notification>();
	List<Notification> list= notificationRepository.findAllByRecieverId(user.getId());
	for(Notification notif:list) {
		if(notif.getSeen()== false) {
			sendList.add(notif);
		}
	}
	return(sendList.size());
}
public  Map<String, Boolean> setNotificationIsSeen(String username) {
	
	Query query = new Query();
	
	User user=userRepository.findUserByUsername(username);
	query.addCriteria(Criteria.where("recieverId").is(user.getId()));
	List<Notification> notif=mongoTemplate.find(query, Notification.class);
	Update update = new Update();
	update.set("seen", true);
	
	mongoTemplate.updateMulti(query, update, Notification.class);
	Map<String, Boolean> response = new HashMap<>();
	response.put("success", Boolean.TRUE);
	return response;
}

}
