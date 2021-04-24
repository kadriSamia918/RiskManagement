package com.telnet.project.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.telnet.project.Entities.ERole;
import com.telnet.project.Entities.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	  Optional<Role> findByName(String name);
	  Role findRoleByName(String name);
	Role findRoleById(String id);
	}
