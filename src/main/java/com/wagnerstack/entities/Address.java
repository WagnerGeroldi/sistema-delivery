package com.wagnerstack.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String street;
	String district;
	String number;
	String complement;
	String cep;

	Client client;

	public Address() {

	}

	public Address(Integer id, String street, String district, String number, String complement, String cep,
			Client client) {
		this.id = id;
		this.street = street;
		this.district = district;
		this.number = number;
		this.complement = complement;
		this.cep = cep;
		this.client = client;
	}

}
