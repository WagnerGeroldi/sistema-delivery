package com.wagnerstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wagnerstack.entities.Category;
import com.wagnerstack.repositories.CategoryRepository;
import com.wagnerstack.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryServices {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	public Category find(Integer id) {
		Optional<Category> item = categoryRepository.findById(id);
		
		return item.orElseThrow(() -> new ObjectNotFoundException("ID não encontrato!"));
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return categoryRepository.save(obj);
	}
	
	public List<Category> findAll() {
		return categoryRepository.findAll();

	}
	
	

}
