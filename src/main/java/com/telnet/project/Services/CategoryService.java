package com.telnet.project.Services;

import java.util.List;

import com.telnet.project.DTO.CategoryDTO;
import com.telnet.project.Entities.Category;

public interface CategoryService {
	
	CategoryDTO createCategory(CategoryDTO category);
	 
	void delete(Category category);
 
    List<CategoryDTO> findAll();
 
    //AttributActif findById(String id);
 
    Category update(Category category);

}
