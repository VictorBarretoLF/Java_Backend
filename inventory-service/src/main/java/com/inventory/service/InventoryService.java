package com.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
	
	private final InventoryRepository inventoryRepository;
	
    @Transactional(readOnly = true)
    public boolean isInStock(List<String> skuCode) {
        return !inventoryRepository.findBySkuCodeIn(skuCode).isEmpty();
    }

}
