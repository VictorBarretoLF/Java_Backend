package com.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productservice.dto.ProductRequest;
import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		
		productRepository.save(product);
		log.info("Product {} is saved", product.getId());
	}
}