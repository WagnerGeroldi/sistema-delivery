package com.wagnerstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wagnerstack.entities.Client;
import com.wagnerstack.repositories.ClientRepository;
import com.wagnerstack.services.exceptions.ObjectNotFoundException;

@Service
public class ClientServices {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	public Client find(Integer id) {
		Optional<Client> item = clientRepository.findById(id);
		
		return item.orElseThrow(() -> new ObjectNotFoundException("ID não encontrato!"));
	}
	
	public List<Client> findAll() {
		return clientRepository.findAll();

	}

}
