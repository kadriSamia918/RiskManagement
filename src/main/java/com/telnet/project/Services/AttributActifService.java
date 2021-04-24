package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.DTO.AttributActifDTO;
import com.telnet.project.Entities.AttributActif;

public interface AttributActifService {
	
	AttributActifDTO createAttribut(AttributActifDTO familleActif);
	 
	 Map<String, Boolean> delete(String id);
 
    List<AttributActifDTO> findAll();
 
    //AttributActif findById(String id);
 
    AttributActif update(AttributActif attributActif);

}
