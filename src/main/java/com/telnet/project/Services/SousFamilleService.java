package com.telnet.project.Services;

import java.util.List;

import com.telnet.project.DTO.SousFamilleDTO;
import com.telnet.project.Entities.SousFamille;

public interface SousFamilleService {
	
	SousFamilleDTO createFamille(SousFamilleDTO famille);
	 
	void delete(SousFamille famille);
 
    List<SousFamilleDTO> findAll();
 
    
 
    SousFamille update(SousFamille famille);

}
