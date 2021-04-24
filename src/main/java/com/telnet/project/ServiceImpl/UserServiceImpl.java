package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.DBRef;
import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Action;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.Risque;
import com.telnet.project.Entities.Role;
import com.telnet.project.Entities.SousFamille;
import com.telnet.project.Entities.User;
import com.telnet.project.Repository.ImageRepository;
import com.telnet.project.Repository.UserRepository;
import com.telnet.project.Services.UserService;
import com.telnet.project.webSocket.Notification;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	MongoTemplate mongoTemplate; 
	@Autowired
	MailServiceImpl mailService; 
	@Override
	public List<User> findAll() 
	{
		return userRepository.findAll();
	}
	public List<User> findNotSeen() 
	{
		Query query = new Query();
		query.fields().include("seen").include("senderId");
		query.addCriteria(Criteria.where("seen").is(false));
		List<Notification> list=mongoTemplate.find(query, Notification.class);
		List<User> usersList = new ArrayList<User>();
		for (Notification notif:list) 
		{
			Query query2 = new Query();
			query2.addCriteria(Criteria.where("id").is(notif.getSenderId()));
			query2.addCriteria(Criteria.where("active").is(false));
			User user = new User();
			user=mongoTemplate.findOne(query2, User.class);
			if(user != null) {
			usersList.add(user);}
		 }
		return usersList;
	}
	public List<User> getNewUsers(){
		return userRepository.findAll();
	}
	@Override
	public User save(User saved) {
		// TODO Auto-generated method stub
		return null;
	}
	public Map<String,Boolean> confirmUser(User  user)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(user.getUsername()));
		User userFound=mongoTemplate.findOne(query, User.class);
		Update update = new Update();
		update.set("active",true);
		mongoTemplate.updateFirst(query, update, User.class);
		Set<Role> newRoles = user.getRoles();
		for(Role role:newRoles) 
		{
	    	Query query2 = new Query();
	    	System.out.println(user.getId());
	    	System.out.println(user.getUsername());
	    	System.out.println(role.getId() + "hiiii");
	    	query2.addCriteria(Criteria.where("id").is(new ObjectId(user.getId())));
	    	
	    	Update update2 = new Update().push("roles", new DBRef("roles", new ObjectId(role.getId())));
	    	mongoTemplate.updateMulti(query2, update2, User.class);
		}
		
		Query query3 = new Query();
		query3.fields().include("seen").include(user.getId());
		query3.addCriteria(Criteria.where("seen").is(false));
		List<Notification> list=mongoTemplate.find(query3, Notification.class);
		for(Notification notif:list) 
		{
			Update update4 = new Update();
			update4.set("seen",true);
			mongoTemplate.updateFirst(query3, update4, Notification.class);
		}
		mailService.SendEmailAfterConfirmAccountWithCC(user.getEmail(), "sanalaadhar6@gmail.com");
		Map<String, Boolean> response = new HashMap<>();
		response.put("UserActive", Boolean.TRUE);
		return response;
		
	}

	public Map<String, Boolean> delete(String id) 
		{
		System.out.println(id + "hi!id");
		User deleted = userRepository.findUserById(id);
		System.out.println(deleted.getUsername() );
		Query query = Query.query(Criteria.where("$id").is(new ObjectId(deleted.getId())));
	//	Update update = new Update().pull("af", query);
		userRepository.delete(deleted);
		Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
		}
	public Map<String,Boolean> setInactive(String id)
		{
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		User userFound=mongoTemplate.findOne(query, User.class);
		Update update = new Update();
		System.out.println(userFound.isActive());
		update.set("active",!userFound.isActive());
		mongoTemplate.updateFirst(query, update, User.class);
		Query query3 = new Query();
		query3.fields().include("seen").include(id);
		query3.addCriteria(Criteria.where("seen").is(false));
		List<Notification> list=mongoTemplate.find(query3, Notification.class);
		for(Notification notif:list) 
		{
			Update update4 = new Update();
			update4.set("seen",true);
			mongoTemplate.updateFirst(query3, update4, Notification.class);
		}
		if(userFound.isActive() == false )
		{
			mailService.SendEmailAfterConfirmAccountWithCC(userFound.getEmail(), "sanalaadhar6@gmail.com");
		}
		Map<String, Boolean> response = new HashMap<>();
		response.put("UserActive", Boolean.TRUE);
		return response;
		
	}
	
	public  Map<String, Boolean> findImage(String username )
	{
		Map<String, Boolean> response = new HashMap<>();
		if(imageRepository.findImageByName(username)== null)
		{
		response.put("found",Boolean.FALSE);	
		}
		else
		{
		response.put("found",Boolean.TRUE);
		}
		return response;
	}
	
	public  Map<String, User> updateUserRole(User userEntry)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(userEntry.getUsername()));
		User user = userRepository.findUserByUsername(userEntry.getUsername());
		User userFound=mongoTemplate.findOne(query, User.class);
		Update update = new Update();
		
		update.set("active",true);
		mongoTemplate.updateFirst(query, update, User.class);
		Set<Role> newRoles = userEntry.getRoles();
		Set<Role> savedRoles = userFound.getRoles();
		Set<Role> setNull =new HashSet();
	   // update.addToSet("roles",user.getRoles());
	    /*for(Role role:newRoles) {
	    	update.push("roles",new DBRef("Role", new ObjectId(role.getId())));
	    }*/
		//update.set("roles", newRoles);
		for(Role savedrole:savedRoles)
		{
			Query query3 = new Query();
	    	query3.addCriteria(Criteria.where("id").is(new ObjectId(user.getId())));
	    	Update update3 = new Update().pull("roles", new DBRef("roles", new ObjectId(savedrole.getId())));
	    	mongoTemplate.updateMulti(query3, update3, User.class);
		}
		for(Role role:newRoles)
		{
	    	Query query2 = new Query();
	    	System.out.println(user.getId());
	    	System.out.println(user.getUsername());
	    	System.out.println(role.getId() + "hiiii");
	    	query2.addCriteria(Criteria.where("id").is(new ObjectId(user.getId())));
	    	Update update2 = new Update().push("roles", new DBRef("roles", new ObjectId(role.getId())));
	    	mongoTemplate.updateMulti(query2, update2, User.class);
		}
		User savedUser = userRepository.findUserByUsername(user.getUsername());
		Map<String, User> response = new HashMap<>();
		response.put("updateUser",savedUser);
		return response;
	}

	public  Map<String, Boolean> updateUser(User user)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(user.getUsername()));
		User userFound=mongoTemplate.findOne(query, User.class);
		Update update = new Update();
		update.set("name", user.getName());
		update.set("lastName", user.getLastName());
		update.set("email", user.getEmail());
		update.set("active",true);
		mongoTemplate.updateFirst(query, update, User.class);
		Set<Role> newRoles = user.getRoles();
		Set<Role> savedRoles = userFound.getRoles();
		Set<Role> setNull =new HashSet<Role>();
	   // update.addToSet("roles",user.getRoles());
	    /*for(Role role:newRoles) {
	    	update.push("roles",new DBRef("Role", new ObjectId(role.getId())));
	    }*/
		//update.set("roles", newRoles);
		
		for(Role savedrole:savedRoles)
		{
			Query query3 = new Query();
	    	
	    	query3.addCriteria(Criteria.where("id").is(new ObjectId(user.getId())));
	    	
	    	Update update3 = new Update().pull("roles", new DBRef("roles", new ObjectId(savedrole.getId())));
	    	mongoTemplate.updateMulti(query3, update3, User.class);
		}
		for(Role role:newRoles) {
	    	Query query2 = new Query();
	    	System.out.println(user.getId());
	    	System.out.println(user.getUsername());
	    	System.out.println(role.getId() + "hiiii");
	    	query2.addCriteria(Criteria.where("id").is(new ObjectId(user.getId())));
	    	
	    	Update update2 = new Update().push("roles", new DBRef("roles", new ObjectId(role.getId())));
	    	mongoTemplate.updateMulti(query2, update2, User.class);
		}
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("updateUser", Boolean.TRUE);
		return response;
	}
	
public Map<String, Boolean> updateLastConnection(String username,Date lastConnection) 
{
	
	Query query = new Query();
	query.addCriteria(Criteria.where("username").is(username));
	User user=mongoTemplate.findOne(query, User.class);
	Update update = new Update();
	update.set("lastConnection", lastConnection);
	mongoTemplate.updateFirst(query, update, User.class);
	Map<String, Boolean> response = new HashMap<>();
	response.put("lastconnection", Boolean.TRUE);
	return response;
}

public User getUserbyUsername(String username)
{
	return userRepository.findUserByUsername(username);
}

public long getUserCount() 
{
	return userRepository.count();
}
}
