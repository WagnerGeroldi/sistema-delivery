package com.wagnerstack.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.wagnerstack.dto.ClientNewDTO;
import com.wagnerstack.entities.Client;
import com.wagnerstack.entities.enums.ClientType;
import com.wagnerstack.repositories.ClientRepository;
import com.wagnerstack.resources.exception.FieldMessage;
import com.wagnerstack.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getType().equals(ClientType.PESSOAFISICA.getCode()) && !BR.isValidCPF(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CPF INVÁLIDO"));
		}
		
		if(objDto.getType().equals(ClientType.PESSOAJURIDICA.getCode()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CNPJ INVÁLIDO"));
		}
		
		
		Client clientEmailTest = clientRepository.findByEmail(objDto.getEmail());
		
		
		if(clientEmailTest != null) {
			list.add(new FieldMessage("email", "Este email já existe"));
		}
			
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}