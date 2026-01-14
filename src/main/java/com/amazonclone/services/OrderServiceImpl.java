package com.amazonclone.services;

import com.amazonclone.dto.OrderDto;
import com.amazonclone.dto.OrderItemDto;
import com.amazonclone.dto.PlaceOrderRequest;
import com.amazonclone.models.*;
import com.amazonclone.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartService cartService;
    private final AddressRepository addressRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public OrderDto placeOrder(PlaceOrderRequest req) {

        User user = getCurrentUser();
        Cart cart = cartService.getCartEntity(user);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Save shipping address
        Address address = Address.builder()
                .houseNumber(req.getHouseNumber())
                .street(req.getStreet())
                .city(req.getCity())
                .state(req.getState())
                .country(req.getCountry())
                .pinCode(req.getPinCode())
                .user(user)
                .build();

        addressRepository.save(address);

        // Create order
        Order order = Order.builder()
                .user(user)
                .amount(cart.getTotalAmount())
                .orderStatus("PLACED")
                .paymentStatus("SUCCESS")
                .shippingAddress(address)
                .build();

        orderRepository.save(order);

        // Convert cart items → order items
        List<OrderItem> orderItems = cart.getItems().stream().map(ci ->
                OrderItem.builder()
                        .order(order)
                        .product(ci.getProduct())
                        .quantity(ci.getQuantity())
                        .price(ci.getPrice())
                        .build()
        ).collect(Collectors.toList());

        orderItemRepository.saveAll(orderItems);

        order.setItems(orderItems);
        orderRepository.save(order);

        // Clear cart
        cartService.clearCart(user);

        return convertToDto(order);
    }

    @Override
    public List<OrderDto> getMyOrders() {
        User user = getCurrentUser();

        return orderRepository.findByUser(user).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private OrderDto convertToDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .amount(order.getAmount())
                .orderStatus(order.getOrderStatus())
                .paymentStatus(order.getPaymentStatus())
                .items(
                        order.getItems().stream().map(oi ->
                                OrderItemDto.builder()
                                        .id(oi.getId())
                                        .productId(oi.getProduct().getId())
                                        .productTitle(oi.getProduct().getTitle())
                                        .quantity(oi.getQuantity())
                                        .price(oi.getPrice())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build();
    }
}
