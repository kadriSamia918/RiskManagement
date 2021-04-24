package com.telnet.project.ServiceImpl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.CategorieIncident;
import com.telnet.project.Entities.Departement;
import com.telnet.project.Repository.CategorieIncidentRepository;
import com.telnet.project.Repository.DepartementRepository;
import com.telnet.project.Services.DepartementService;
@Service
public class DepartementServiceImp implements DepartementService {
	@Autowired
	private DepartementRepository departementRepository;
		
		
		
		@Override
		public List<Departement> findAll() {
		
				return departementRepository.findAll();
			}


		public Departement createDepartement(@Valid Departement dp) {
		
			Departement saved = new Departement();
			saved.setNom(dp.getNom());

			
				return departementRepository.save(saved);
			}


		

}
