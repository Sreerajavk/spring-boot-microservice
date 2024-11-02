package com.sreers.microservice.inventory.repository;

import com.sreers.microservice.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
}
