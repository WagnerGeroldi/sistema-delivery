package com.wagnerstack.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.wagnerstack.entities.Client;
import com.wagnerstack.services.validation.ClientUpdate;


@ClientUpdate
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Não pode ficar vazio")
	@Length(min = 5, max = 50, message = "O tamanho deve ser entre 5 e 50 caracteres")
	private String name;

	@NotEmpty(message = "Não pode ficar vazio")
	@Email(message = "E-mail inválido")
	private String email;

	public ClientDTO() {

	}
	
	public ClientDTO(Client obj	) {
		
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail(); 

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
