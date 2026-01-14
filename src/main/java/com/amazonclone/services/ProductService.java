package com.amazonclone.services;

import com.amazonclone.dto.ProductDto;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto dto);
    List<ProductDto> getAllProducts();
    List<ProductDto> getProductsByCategory(Long categoryId);
    ProductDto getProductById(Long id);
}
