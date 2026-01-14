package com.amazonclone;

import com.amazonclone.dto.CategoryDto;
import com.amazonclone.models.Category;
import com.amazonclone.repositories.CategoryRepository;
import com.amazonclone.services.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCategory() {

        CategoryDto dto = new CategoryDto(null, "Sports", "sports items");

        Category saved = new Category(1L, "Sports", "sports items", null);

        when(categoryRepository.save(any())).thenReturn(saved);

        CategoryDto result = categoryService.createCategory(dto);

        assertEquals("Sports", result.getName());
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetAllCategories() {

        Category c = new Category(1L, "Fashion", "clothes", null);

        when(categoryRepository.findAll()).thenReturn(List.of(c));

        var result = categoryService.getAllCategories();

        assertEquals(1, result.size());
        assertEquals("Fashion", result.get(0).getName());
    }
}
