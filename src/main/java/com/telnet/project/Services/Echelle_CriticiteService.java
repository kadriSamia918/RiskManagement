package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.DTO.Echelle_CriticiteDTO;
import com.telnet.project.Entities.Echelle_Criticite;

public interface Echelle_CriticiteService {

	
	 
	Map<String, Boolean> delete(String id);
 
    List<Echelle_CriticiteDTO> findAll();
 

    Echelle_Criticite update(Echelle_Criticite echelle_Criticite);

}
