package com.sreers.microservices.product.controller;

import com.sreers.microservices.product.controller.dto.ProductRequest;
import com.sreers.microservices.product.controller.dto.ProductResponse;
import com.sreers.microservices.product.repository.ProductRepository;
import com.sreers.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
