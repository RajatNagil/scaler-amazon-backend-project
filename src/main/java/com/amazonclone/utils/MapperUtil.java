package com.amazonclone.utils;

import com.amazonclone.dto.*;
import com.amazonclone.models.*;

import java.util.stream.Collectors;

public class MapperUtil {

    public static ProductDto toProductDto(Product p) {
        return ProductDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .imageUrl(p.getImageUrl())
                .price(p.getPrice())
                .stock(p.getStock())
                .categoryId(p.getCategory().getId())
                .categoryName(p.getCategory().getName())
                .build();
    }

    public static CategoryDto toCategoryDto(Category c) {
        return CategoryDto.builder()
                .id(c.getId())
                .name(c.getName())
                .description(c.getDescription())
                .build();
    }

    public static CartItemDto toCartItemDto(CartItem ci) {
        return CartItemDto.builder()
                .id(ci.getId())
                .productId(ci.getProduct().getId())
                .productTitle(ci.getProduct().getTitle())
                .quantity(ci.getQuantity())
                .price(ci.getPrice())
                .build();
    }

    public static CartDto toCartDto(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .totalAmount(cart.getTotalAmount())
                .items(cart.getItems().stream()
                        .map(MapperUtil::toCartItemDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public static OrderItemDto toOrderItemDto(OrderItem oi) {
        return OrderItemDto.builder()
                .id(oi.getId())
                .productId(oi.getProduct().getId())
                .productTitle(oi.getProduct().getTitle())
                .quantity(oi.getQuantity())
                .price(oi.getPrice())
                .build();
    }

    public static OrderDto toOrderDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .amount(order.getAmount())
                .orderStatus(order.getOrderStatus())
                .paymentStatus(order.getPaymentStatus())
                .items(order.getItems().stream()
                        .map(MapperUtil::toOrderItemDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
