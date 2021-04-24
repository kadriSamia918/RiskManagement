package com.telnet.project.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Evenement;
import com.telnet.project.Entities.EvenementNature;
import com.telnet.project.Repository.EvenementNatureRepository;
import com.telnet.project.Services.EvenementNatureService;
@Service
public class EvenementNatureServiceImp implements EvenementNatureService {
	@Autowired
	private EvenementNatureRepository evenementNatureRepository;
	
	public Map<String, EvenementNature> ajoutNatureEvenement(@Valid EvenementNature evenementEntry) {
		Map<String, EvenementNature> response = new HashMap<>();
		EvenementNature evenement = new EvenementNature();
		String nature = evenementEntry.getNature();
		evenement.setNature(nature);		
	
		evenementNatureRepository.save(evenement);
		 response.put("evenement_Ajoutée",evenement);
		 System.out.println(response);
	  
		return response;}

	public List<EvenementNature> findAll() {
		return evenementNatureRepository.findAll();
	}
}
