package com.wagnerstack.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wagnerstack.entities.Category;
import com.wagnerstack.services.CategoryServices;

@RestController
@RequestMapping(value = "/category")
public class CategoryResource {

	@Autowired
	private CategoryServices service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> find(@PathVariable Integer id) {

		Category obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Category>> findAll() {

		List<Category> obj = service.findAll();

		return ResponseEntity.ok().body(obj);
	}

}
