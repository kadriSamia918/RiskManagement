package com.telnet.project.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.telnet.project.Entities.User;

public interface UserRepository extends MongoRepository<User, String> {
	  Optional<User> findByUsername(String username);

	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);
	  
	  User findUserByUsername(String userString);

	User findUserById(String id);

	User findUserByEmail(String email);

	//User findUserByRoles(String name);
	  
	}