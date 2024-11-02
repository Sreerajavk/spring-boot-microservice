package com.sreers.microservice.order.controller;

import com.sreers.microservice.order.dto.OrderRequest;
import com.sreers.microservice.order.dto.OrderResponse;
import com.sreers.microservice.order.model.Order;
import com.sreers.microservice.order.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
        // return "Order Placed Successfully";
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return  orderService.getAllOrders();
    }
}
