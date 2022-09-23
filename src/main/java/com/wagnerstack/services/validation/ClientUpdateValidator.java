package com.wagnerstack.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.wagnerstack.dto.ClientDTO;
import com.wagnerstack.entities.Client;
import com.wagnerstack.repositories.ClientRepository;
import com.wagnerstack.resources.exception.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
		
		Integer uriId = Integer.parseInt(map.get("id"));
			
		List<FieldMessage> list = new ArrayList<>();
		
		
		Client clientEmailTest = clientRepository.findByEmail(objDto.getEmail());
				
		if(clientEmailTest != null && !clientEmailTest.getId().equals(uriId)) {
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