package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.DTO.FamilleDTO;

import com.telnet.project.Entities.Famille;

public interface FamilleService {
	
	Famille createFamille(Famille famille);
	 
	Map<String, Boolean> delete(String id);
 
    List<Famille> findAll();
 
    //AttributActif findById(String id);
 
    Famille update(Famille famille);

}
