package com.wagnerstack.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wagnerstack.entities.City;
import com.wagnerstack.services.CityServices;

@RestController
@RequestMapping(value = "/city")
public class CityResource {

	@Autowired
	private CityServices service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<City> find(@PathVariable Integer id) {

		City obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<City>> findAll() {

		List<City> obj = service.findAll();

		return ResponseEntity.ok().body(obj);
	}

}
