package com.telnet.project.ServiceImpl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.DTO.CategoryDTO;
import com.telnet.project.Entities.Category;
import com.telnet.project.Repository.CategoryRepository;
import com.telnet.project.Services.CategoryService;

@Service
public class CategoryServiceImpl  implements CategoryService  {
	
	  private  CategoryRepository categoryRepository; 
		

	    @Autowired
	    CategoryServiceImpl(CategoryRepository categoryRepository) {
	        this.categoryRepository = categoryRepository;
	    }

	@Override
	public CategoryDTO createCategory(CategoryDTO category) {
		Category persisted = Category.getBuilder()
                .nom(category.getNom())
                .description(category.getDescription())
                .build();
        persisted = categoryRepository.save(persisted);
        return convertCategory(persisted);
	}

	@Override
	public void delete(Category category) {
		categoryRepository.delete(category);
		
	}

	@Override
	public List<CategoryDTO> findAll() {
		   List<Category> categoryEntries = categoryRepository.findAll();
	        return convertCategorys(categoryEntries);
	}

	@Override
	public Category update(Category category) {
		Optional<Category> updated = categoryRepository.findById(category.getId());
		Category v =updated.get();
		v.setDescription(category.getDescription());
		v.setNom(category.getNom());
		
		
		return categoryRepository.save(v);
	}
	private CategoryDTO convertCategory(Category model) {
			CategoryDTO dto = new  CategoryDTO();
	 
	        dto.setId(model.getId());
	        dto.setNom(model.getNom());
	        dto.setDescription(model.getDescription());
	 
	        return dto;
	    }
    private List<CategoryDTO> convertCategorys(List<Category> models) {
        return models.stream()
                .map(this::convertCategory)
                .collect(toList());
    }
}
