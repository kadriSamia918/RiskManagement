package com.telnet.project.ServiceImpl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Action;
import com.telnet.project.Entities.CategorieAction;
import com.telnet.project.Entities.Personne;
import com.telnet.project.Repository.ActifRepository;
import com.telnet.project.Repository.CategorieActionRepository;
import com.telnet.project.Services.CategorieActionService;

@Service
public class CategorieActionServiceImpl implements CategorieActionService {
	@Autowired
	private CategorieActionRepository categorieActionRepository;
	
	
	@Override
	public List<CategorieAction> findAll() {
	
			return categorieActionRepository.findAll();
		}


	public CategorieAction createActionCategorie(@Valid CategorieAction action) {
	
		CategorieAction saved = new CategorieAction();
		saved.setCategorie(action.getCategorie());

		
			return categorieActionRepository.save(saved);
		}
	}



