package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.Entities.Role;

public interface RolesService {

	 List<Role> findAll();	    
	 Map<String, Role> save(Role saved);
}
