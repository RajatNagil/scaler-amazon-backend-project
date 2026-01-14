package com.amazonclone.controllers;

import com.amazonclone.dto.AddToCartRequest;
import com.amazonclone.dto.CartDto;
import com.amazonclone.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public CartDto getCart() {
        return cartService.getMyCart();
    }

    @PostMapping("/add")
    public CartDto addToCart(@RequestBody AddToCartRequest request) {
        return cartService.addToCart(request);
    }

    @DeleteMapping("/remove/{id}")
    public CartDto remove(@PathVariable Long id) {
        return cartService.removeItem(id);
    }
}
