package com.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.orderservice.dto.OrderLineItemsDto;
import com.orderservice.dto.OrderRequest;
import com.orderservice.model.Order;
import com.orderservice.model.OrderLineItems;
import com.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final WebClient webClient;
	
	@Transactional
	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
			.stream()
			.map(orderLineItemsDto -> mapToDto(orderLineItemsDto))
			.toList();
		
		order.setOrderLineItemsList(orderLineItems);
		
		List<String> skuCodes = order.getOrderLineItemsList()
				.stream()
				.map(orderLineItem -> orderLineItem.getSkuCode())
				.toList();
		
		// Call Inventory Service, and place order if product is in Stock
		// TODO: CONCETAR O ERRO AO DAR UM PARSE NO JSON, CONVERTER PARA O OBJETO CORRETO.
		Boolean result = webClient.get()
			.uri("http://localhost:8080/api/inventory?skuCode=iphone_12")
			.retrieve()
			.bodyToMono(Boolean.class)
			.block();
		
		if(result) {
			orderRepository.save(order);
		} else {
			throw new IllegalArgumentException("Product is not in stock, please try again latter");
		}

	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}
}
