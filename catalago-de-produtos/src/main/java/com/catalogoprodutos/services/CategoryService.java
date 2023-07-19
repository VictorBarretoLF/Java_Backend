package com.catalogoprodutos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogoprodutos.entities.Category;
import com.catalogoprodutos.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
}
