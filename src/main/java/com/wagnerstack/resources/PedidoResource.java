package com.wagnerstack.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wagnerstack.entities.Pedido;
import com.wagnerstack.services.PedidoServices;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoResource {

	@Autowired
	private PedidoServices service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {

		Pedido obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pedido>> findAll() {

		List<Pedido> obj = service.findAll();

		return ResponseEntity.ok().body(obj);
	}

}
