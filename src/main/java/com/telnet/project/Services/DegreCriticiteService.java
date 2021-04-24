package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.Entities.DegreCriticite;

public interface DegreCriticiteService {
	
	Map<String, Boolean> delete(String id);
	 
    List<DegreCriticite> findAll();
 

    DegreCriticite update(DegreCriticite degreCriticite);


}
