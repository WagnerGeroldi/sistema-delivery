package com.wagnerstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wagnerstack.dto.CategoryDTO;
import com.wagnerstack.entities.Category;
import com.wagnerstack.repositories.CategoryRepository;
import com.wagnerstack.services.exceptions.DataIntegrityException;
import com.wagnerstack.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryServices {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category find(Integer id) {
		Optional<Category> item = categoryRepository.findById(id);

		return item.orElseThrow(() -> new ObjectNotFoundException("ID não encontrato!"));
	}

	public Category insert(Category obj) {
		obj.setId(null);
		return categoryRepository.save(obj);
	}

	public Category update(Category obj) {
		Category newObj = find(obj.getId());
		updateData(newObj, obj);
		return categoryRepository.save(newObj);
	}

	/*metodo auxiliar para update */
	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
		
	}

	public void delete(Integer id) {
		find(id);

		try {

			categoryRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que está em uso");

		}
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();

	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoryRepository.findAll(pageRequest);


	}
	
	public Category fromDTO(CategoryDTO objDto) {
		return new Category(objDto.getId(), objDto.getName());
	}

}
