package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
	
	
	 
	   List<Category> findAll();
	   
	   Category save(Category saved);
	    
	   Category findCategoryByNom(String nom);
	   
	
}