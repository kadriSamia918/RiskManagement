package com.telnet.project.Services;

import java.util.List;

import com.telnet.project.Entities.Personne;

public interface PersonneService {

	List<Personne> findAll();


	


	Personne findPersonneById(String id);
}
