package com.wagnerstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wagnerstack.entities.Product;
import com.wagnerstack.repositories.ProductRepository;

@Service
public class ProductServices {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	public Product find(Integer id) {
		Optional<Product> item = productRepository.findById(id);
		return item.orElse(null);
	}
	
	public List<Product> findAll() {
		return productRepository.findAll();

	}

}
