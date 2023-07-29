package com.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.InventoryResponse;
import com.inventory.model.Inventory;
import com.inventory.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
	
	private final InventoryRepository inventoryRepository;
	
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
        		.stream()
        		.map(inventory -> mapToIventoryResponse(inventory))
        		.toList();
    }
    
    public InventoryResponse mapToIventoryResponse(Inventory inventory) {
    	return InventoryResponse.builder()
    			.skuCode(inventory.getSkuCode())
    			.isInStock(inventory.getQuantity() > 0)
    			.build();
    }

}
