package com.amazonclone.services;

import com.amazonclone.dto.OrderDto;
import com.amazonclone.dto.PlaceOrderRequest;

import java.util.List;

public interface OrderService {
    OrderDto placeOrder(PlaceOrderRequest request);
    List<OrderDto> getMyOrders();
}
