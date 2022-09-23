package com.wagnerstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wagnerstack.dto.ClientDTO;
import com.wagnerstack.dto.ClientNewDTO;
import com.wagnerstack.entities.Address;
import com.wagnerstack.entities.City;
import com.wagnerstack.entities.Client;
import com.wagnerstack.entities.enums.ClientType;
import com.wagnerstack.repositories.AddressRepository;
import com.wagnerstack.repositories.ClientRepository;
import com.wagnerstack.services.exceptions.DataIntegrityException;
import com.wagnerstack.services.exceptions.ObjectNotFoundException;

@Service
public class ClientServices {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	public Client find(Integer id) {
		Optional<Client> item = clientRepository.findById(id);

		return item.orElseThrow(() -> new ObjectNotFoundException("ID não encontrato!"));
	}

	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = clientRepository.save(obj);
		addressRepository.saveAll(obj.getAdresses());
		return obj;
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return clientRepository.save(newObj);
	}

	/* metodo auxiliar para update */
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());

	}

	public void delete(Integer id) {
		find(id);

		try {

			clientRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um clientente que tem pedidos em aberto");

		}
	}

	public List<Client> findAll() {
		return clientRepository.findAll();

	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);

	}

	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}

	public Client fromDTO(ClientNewDTO objDto) {
		Client client = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(),
				ClientType.toEnum(objDto.getType()));
		City city = new City(objDto.getCidadeId(), null, null);
		Address address = new Address(null, objDto.getStreet(), objDto.getDistrict(), objDto.getNumber(),
				objDto.getComplement(), objDto.getCep(), client, city);
		client.getAdresses().add(address);
		client.getPhones().add(objDto.getPhone1());
		if (objDto.getPhone2() != null) {
			client.getPhones().add(objDto.getPhone2());
		}
		if (objDto.getPhone3() != null) {
			client.getPhones().add(objDto.getPhone3());
		}
		
		return client;
	}

}
