package com.sreers.microservice.inventory.service;

import com.sreers.microservice.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    final InventoryRepository inventoryRepository;
    public boolean isInStock(String skuCode, Integer quantity) {
        log.info(skuCode);
        log.info(String.valueOf(quantity));
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);
    }
}
