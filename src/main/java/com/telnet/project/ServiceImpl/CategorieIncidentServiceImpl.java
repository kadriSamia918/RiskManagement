package com.telnet.project.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.CategorieIncident;
import com.telnet.project.Entities.Category;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Repository.CategorieIncidentRepository;

import com.telnet.project.Services.CategorieIncidentService;
@Service

public class CategorieIncidentServiceImpl implements CategorieIncidentService {

		@Autowired
		private CategorieIncidentRepository categorieIncidentRepository;
		
		
		@Override
		public List<CategorieIncident> findAll() {
		
				return categorieIncidentRepository.findAll();
			}


		public CategorieIncident createIncidentCategorie(@Valid CategorieIncident action) {
		
			CategorieIncident saved = new CategorieIncident();
			saved.setCategorie(action.getCategorie());
			saved.setDescription(action.getDescription());

			
				return categorieIncidentRepository.save(saved);
			}


		@Override
		public Map<String, Boolean> delete(String id) {
			CategorieIncident deleted = categorieIncidentRepository.findCategorieIncidentById(id);
			
			categorieIncidentRepository.delete(deleted);
			 Map<String, Boolean> response = new HashMap<>();
		        response.put("deleted", Boolean.TRUE);
		        return response;
		}
		}
