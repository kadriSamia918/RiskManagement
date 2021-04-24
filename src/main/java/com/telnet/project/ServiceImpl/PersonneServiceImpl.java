package com.telnet.project.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Personne;
import com.telnet.project.Repository.PersonneRepository;
import com.telnet.project.Services.PersonneService;

@Service 
public class PersonneServiceImpl implements PersonneService{
	
	@Autowired
	private PersonneRepository personneRepository;
	
	@Override
	public List<Personne> findAll() {
		
		return personneRepository.findAll();
	}

	@Override
	public Personne findPersonneById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
