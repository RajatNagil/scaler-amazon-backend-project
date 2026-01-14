package com.amazonclone.services;

import com.amazonclone.dto.AddToCartRequest;
import com.amazonclone.dto.CartDto;
import com.amazonclone.dto.CartItemDto;
import com.amazonclone.models.*;
import com.amazonclone.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public void createCart(User user) {
        Cart cart = Cart.builder()
                .user(user)
                .totalAmount(0.0)
                .build();
        cartRepository.save(cart);
    }

    @Override
    public Cart getCartEntity(User user) {
        return cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public CartDto getMyCart() {
        User user = getCurrentUser();
        Cart cart = getCartEntity(user);

        return convertToDto(cart);
    }

    @Override
    public CartDto addToCart(AddToCartRequest request) {

        User user = getCurrentUser();
        Cart cart = getCartEntity(user);

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Invalid product"));

        // Check if item already exists in cart
        CartItem existing = cart.getItems().stream()
                .filter(it -> it.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + request.getQuantity());
            existing.setPrice(existing.getQuantity() * product.getPrice());
            cartItemRepository.save(existing);
        } else {
            CartItem item = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .price(product.getPrice() * request.getQuantity())
                    .build();

            cartItemRepository.save(item);
        }

        // Update cart total
        double total = cart.getItems().stream()
                .mapToDouble(CartItem::getPrice)
                .sum();

        cart.setTotalAmount(total);
        cartRepository.save(cart);

        return convertToDto(cart);
    }

    @Override
    public CartDto removeItem(Long itemId) {

        User user = getCurrentUser();
        Cart cart = getCartEntity(user);

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Unauthorized removal");
        }

        cartItemRepository.delete(item);

        double total = cart.getItems().stream()
                .filter(i -> !i.getId().equals(itemId)) // exclude removed
                .mapToDouble(CartItem::getPrice)
                .sum();

        cart.setTotalAmount(total);
        cartRepository.save(cart);

        return convertToDto(cart);
    }

    @Override
    public void clearCart(User user) {
        Cart cart = getCartEntity(user);

        cart.getItems().clear();
        cart.setTotalAmount(0.0);

        cartRepository.save(cart);
    }

    private CartDto convertToDto(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .totalAmount(cart.getTotalAmount())
                .items(
                        cart.getItems().stream().map(it ->
                                CartItemDto.builder()
                                        .id(it.getId())
                                        .productId(it.getProduct().getId())
                                        .productTitle(it.getProduct().getTitle())
                                        .quantity(it.getQuantity())
                                        .price(it.getPrice())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build();
    }
}
