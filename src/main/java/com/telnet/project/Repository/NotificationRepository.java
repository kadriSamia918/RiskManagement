package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Personne;
import com.telnet.project.webSocket.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
	
	 
	   List<Notification> findAll();
	   List<Notification> findAllByRecieverId(String recieverId);
	  
	 /*
	    
	    Actif save(Actif saved);


		List<Actif> findActifByProprietaire(Personne proprietaire);


		Actif findActifById(String id);*/
}
