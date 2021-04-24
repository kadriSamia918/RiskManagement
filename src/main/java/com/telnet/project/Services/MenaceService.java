package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Menace;


public interface MenaceService {
	
	Map<String, Menace> createMenace(Menace menace);
	 
	Map<String, Boolean> delete(String id);
 
    List<Menace> findAll();
 
    Menace update(Menace menace);

}
