package com.sreers.microservice.inventory.controller;

import com.sreers.microservice.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    boolean isInStock(@RequestParam String skuCode, Integer quantity) {
        return inventoryService.isInStock(skuCode,quantity);
    }


}
