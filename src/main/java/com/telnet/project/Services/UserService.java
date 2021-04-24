package com.telnet.project.Services;

import java.util.List;


import com.telnet.project.Entities.User;

public interface UserService {

	
	   List<User> findAll();	    
	   User save(User saved);




}
