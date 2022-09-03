package com.wagnerstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wagnerstack.entities.City;
import com.wagnerstack.repositories.CityRepository;
import com.wagnerstack.services.exceptions.ObjectNotFoundException;

@Service
public class CityServices {
	
	@Autowired
	private CityRepository cityRepository;
	
	
	public City find(Integer id) {
		Optional<City> item = cityRepository.findById(id);
		
		return item.orElseThrow(() -> new ObjectNotFoundException("ID não encontrato!"));
	}
	
	public List<City> findAll() {
		return cityRepository.findAll();

	}

}
