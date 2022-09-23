package com.wagnerstack.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.wagnerstack.services.validation.ClientInsert;


@ClientInsert
public class ClientNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Não pode ficar vazio")
	@Length(min = 5, max = 50, message = "O tamanho deve ser entre 5 e 50 caracteres")
	private String name;
	
	@NotEmpty(message = "Não pode ficar vazio")
	@Email(message= "Email inválido")
	private String email;
	
	@NotEmpty(message = "Não pode ficar vazio")
	private String cpfOrCnpj;
	
	private Integer type;

	@NotEmpty(message = "Não pode ficar vazio")
	private String street;
	
	@NotEmpty(message = "Não pode ficar vazio")
	private String district;
	
	@NotEmpty(message = "Não pode ficar vazio")
	private String number;
	
	private String complement;
	
	@NotEmpty(message = "Não pode ficar vazio")
	private String cep;

	@NotEmpty(message = "Não pode ficar vazio")
	private String phone1;
	
	private String phone2;
	private String phone3;

	private Integer cidadeId;

	public ClientNewDTO() {

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

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}



	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

}
