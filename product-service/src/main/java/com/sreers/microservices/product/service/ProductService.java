package com.sreers.microservices.product.service;

import com.sreers.microservices.product.controller.dto.ProductRequest;
import com.sreers.microservices.product.controller.dto.ProductResponse;
import com.sreers.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;


    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product().builder()
                .name(productRequest.name())
                .id(productRequest.id())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product created successfully");
        return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> {
                    return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
                }).toList();
    }
}