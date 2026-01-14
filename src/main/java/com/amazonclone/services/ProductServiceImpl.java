package com.amazonclone.services;

import com.amazonclone.dto.ProductDto;
import com.amazonclone.models.Category;
import com.amazonclone.models.Product;
import com.amazonclone.repositories.CategoryRepository;
import com.amazonclone.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto createProduct(ProductDto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .category(category)
                .build();

        Product saved = productRepository.save(product);

        return ProductDto.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .description(saved.getDescription())
                .price(saved.getPrice())
                .stock(saved.getStock())
                .imageUrl(saved.getImageUrl())
                .categoryId(category.getId())
                .categoryName(category.getName())
                .build();
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(p ->
                ProductDto.builder()
                        .id(p.getId())
                        .title(p.getTitle())
                        .description(p.getDescription())
                        .price(p.getPrice())
                        .stock(p.getStock())
                        .imageUrl(p.getImageUrl())
                        .categoryId(p.getCategory().getId())
                        .categoryName(p.getCategory().getName())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return productRepository.findByCategory(category).stream().map(p ->
                ProductDto.builder()
                        .id(p.getId())
                        .title(p.getTitle())
                        .description(p.getDescription())
                        .price(p.getPrice())
                        .stock(p.getStock())
                        .imageUrl(p.getImageUrl())
                        .categoryId(category.getId())
                        .categoryName(category.getName())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {

        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return ProductDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .price(p.getPrice())
                .stock(p.getStock())
                .imageUrl(p.getImageUrl())
                .categoryId(p.getCategory().getId())
                .categoryName(p.getCategory().getName())
                .build();
    }
}
