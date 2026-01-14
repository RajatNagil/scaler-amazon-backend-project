package com.amazonclone;

import com.amazonclone.dto.ProductDto;
import com.amazonclone.models.Category;
import com.amazonclone.models.Product;
import com.amazonclone.repositories.CategoryRepository;
import com.amazonclone.repositories.ProductRepository;
import com.amazonclone.services.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Category c = new Category(1L, "Electronics", "desc", new ArrayList<>());

        ProductDto dto = ProductDto.builder()
                .title("iPhone")
                .description("Apple phone")
                .price(999.0)
                .stock(10)
                .categoryId(1L)
                .build();

        Product saved = Product.builder()
                .id(101L)
                .title(dto.getTitle())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .category(c)
                .build();

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(c));
        when(productRepository.save(any())).thenReturn(saved);

        var result = productService.createProduct(dto);

        assertEquals("iPhone", result.getTitle());
        assertEquals(101L, result.getId());
    }

    @Test
    void testGetAllProducts() {

        Category c = new Category(1L, "Electronics", "desc", new ArrayList<>());

        Product p = Product.builder()
                .id(1L)
                .title("Laptop")
                .category(c)
                .build();

        when(productRepository.findAll()).thenReturn(List.of(p));

        var result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getTitle());
    }
}
