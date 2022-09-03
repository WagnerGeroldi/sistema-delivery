package com.wagnerstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wagnerstack.entities.Country;
import com.wagnerstack.repositories.CountryRepository;
import com.wagnerstack.services.exceptions.ObjectNotFoundException;

@Service
public class CountryServices {
	
	@Autowired
	private CountryRepository countryRepository;
	
	
	public Country find(Integer id) {
		Optional<Country> item = countryRepository.findById(id);
		
		return item.orElseThrow(() -> new ObjectNotFoundException("ID não encontrato!"));
	}
	
	public List<Country> findAll() {
		return countryRepository.findAll();

	}

}
