package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.Entities.Menace;
import com.telnet.project.Entities.Vulnerabilite;

public interface VulnerabiliteService {
	
	 Vulnerabilite createVulnerabilite(Vulnerabilite vulnerabilite);
	 
	Map<String, Boolean> delete(String id);
 
    List<Vulnerabilite> findAll();
 
    //AttributActif findById(String id);
 
    Vulnerabilite update(Vulnerabilite vulenerabilite);

}
