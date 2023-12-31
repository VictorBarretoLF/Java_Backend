package com.catalogoprodutos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catalogoprodutos.dto.CategoryDTO;
import com.catalogoprodutos.entities.Category;
import com.catalogoprodutos.repositories.CategoryRepository;
import com.catalogoprodutos.services.exceptions.DatabaseException;
import com.catalogoprodutos.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
		Page<Category> list = categoryRepository.findAll(pageRequest);
		return list.map(category -> new CategoryDTO(category));
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = categoryRepository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = categoryRepository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO categoryDTOUpdated) {
		Optional<Category> obj = categoryRepository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
		entity.setName(categoryDTOUpdated.getName());

		return new CategoryDTO(entity);
	}

	public void delete(Long id) {
		try {
			categoryRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

}
