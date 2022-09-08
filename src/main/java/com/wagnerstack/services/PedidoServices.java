package com.wagnerstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wagnerstack.entities.Pedido;
import com.wagnerstack.repositories.PedidoRepository;

@Service
public class PedidoServices {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public Pedido find(Integer id) {
		Optional<Pedido> item = pedidoRepository.findById(id);
		return item.orElse(null);
	}
	
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();

	}

}
