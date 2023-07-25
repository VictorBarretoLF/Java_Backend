package com.productservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.productservice.dto.ProductRequest;
import com.productservice.repository.ProductRepository;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.23");
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestString))
        		.andExpect(status().isCreated());
        Assertions.assertEquals(1, productRepository.findAll().size());
	}
	
	@Test
	void shouldGetAllProducts() throws Exception {
	    // First, create some products to test with
	    ProductRequest productRequest1 = getProductRequest();
	    String productRequestString1 = objectMapper.writeValueAsString(productRequest1);

	    mockMvc.perform(MockMvcRequestBuilders.post("/product")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(productRequestString1))
	            .andExpect(status().isCreated());

	    ProductRequest productRequest2 = getProductRequest();
//	    String productRequestString2 = objectMapper.writeValueAsString(productRequest2);
//
//	    mockMvc.perform(MockMvcRequestBuilders.post("/product")
//	            .contentType(MediaType.APPLICATION_JSON)
//	            .content(productRequestString2))
//	            .andExpect(status().isCreated());

	    // Then, make a GET request to the /product endpoint
	    mockMvc.perform(MockMvcRequestBuilders.get("/product")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", hasSize(2))) // I should expects 3 if you uncomment the code above
	            .andExpect(jsonPath("$[0].name", is("Iphone 20"))) // Assert that the name of the first product is correct
	            .andExpect(jsonPath("$[1].name", is("Iphone 20"))); // Assert that the name of the second product is correct
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("Iphone 20")
				.description("Iphone 20 é o ultimo da linha")
				.price(BigDecimal.valueOf(20000))
				.build();
	}

}
