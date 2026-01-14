package com.amazonclone.services;

import com.amazonclone.dto.AddToCartRequest;
import com.amazonclone.dto.CartDto;
import com.amazonclone.models.User;
import com.amazonclone.models.Cart;

public interface CartService {
    void createCart(User user);
    CartDto getMyCart();
    CartDto addToCart(AddToCartRequest request);
    CartDto removeItem(Long itemId);
    void clearCart(User user);
    Cart getCartEntity(User user);
}
