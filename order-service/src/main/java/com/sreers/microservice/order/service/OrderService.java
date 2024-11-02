package com.sreers.microservice.order.service;

import com.sreers.microservice.order.client.InventoryClient;
import com.sreers.microservice.order.dto.OrderRequest;
import com.sreers.microservice.order.dto.OrderResponse;
import com.sreers.microservice.order.model.Order;
import com.sreers.microservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public ResponseEntity createOrder(OrderRequest orderRequest) {
        if(inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity())) {
            Order order = new Order().builder()
                    .id(orderRequest.id())
                    .orderNumber(orderRequest.orderNumber())
                    .skuCode(orderRequest.skuCode())
                    .price(orderRequest.price())
                    .quantity(orderRequest.quantity())
                    .build();
            orderRepository.save(order);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}