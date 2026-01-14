package com.amazonclone.controllers;

import com.amazonclone.dto.ProductDto;
import com.amazonclone.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto dto) {
        return productService.createProduct(dto);
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/category/{id}")
    public List<ProductDto> getProductsByCategory(@PathVariable Long id) {
        return productService.getProductsByCategory(id);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
