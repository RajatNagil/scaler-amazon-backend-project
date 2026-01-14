package com.amazonclone.controllers;

import com.amazonclone.dto.OrderDto;
import com.amazonclone.dto.PlaceOrderRequest;
import com.amazonclone.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    public OrderDto placeOrder(@RequestBody PlaceOrderRequest request) {
        return orderService.placeOrder(request);
    }

    @GetMapping
    public List<OrderDto> getMyOrders() {
        return orderService.getMyOrders();
    }
}
