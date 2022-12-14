package com.wagnerstack.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wagnerstack.entities.Country;
import com.wagnerstack.services.CountryServices;

@RestController
@RequestMapping(value = "/country")
public class CountryResource {

	@Autowired
	private CountryServices service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Country> find(@PathVariable Integer id) {

		Country obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Country>> findAll() {

		List<Country> obj = service.findAll();

		return ResponseEntity.ok().body(obj);
	}

}
