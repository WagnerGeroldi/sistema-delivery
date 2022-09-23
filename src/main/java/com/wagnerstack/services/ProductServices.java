package com.wagnerstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wagnerstack.entities.Category;
import com.wagnerstack.entities.Product;
import com.wagnerstack.repositories.CategoryRepository;
import com.wagnerstack.repositories.ProductRepository;

@Service
public class ProductServices {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	public Product find(Integer id) {
		Optional<Product> item = productRepository.findById(id);
		return item.orElse(null);
	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Category> categories = categoryRepository.findAllById(ids);
		
		return productRepository.search(name, categories, pageRequest);
		
		
	}
	
	public List<Product> findAll() {
		return productRepository.findAll();

	}
	
	

}
