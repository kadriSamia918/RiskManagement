package com.telnet.project.Controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.DTO.CategoryDTO;
import com.telnet.project.Entities.Category;
import com.telnet.project.ServiceImpl.CategoryServiceImpl;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class CategoryController {
	
   private   CategoryServiceImpl service;
	   
	   @Autowired
	   CategoryController( CategoryServiceImpl service) {
	        this.service = service;
	    }
	  
	
	    @RequestMapping(value = "/createCategory",method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    CategoryDTO create(@RequestBody @Valid CategoryDTO category) {
	        return service.createCategory(category);
	    }
	   
	    @RequestMapping(method = RequestMethod.GET,value = "/afficherCategory")
	    List<CategoryDTO> findAll() {
	        return service.findAll();
	    }
	    
	    @RequestMapping(method = RequestMethod.PUT,value = "/updateCategory/{id}")  
	    @ResponseStatus(HttpStatus.CREATED)
	    	Category update (@RequestBody @Valid Category categoryEntry)
	    	{
	    	return service.update(categoryEntry);
	    }
	    
	    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteCategory/{id}")     
	    void delete(@RequestBody @Valid Category categoryEntry) {
	    service.delete(categoryEntry);
	    
	    }
	    
}
