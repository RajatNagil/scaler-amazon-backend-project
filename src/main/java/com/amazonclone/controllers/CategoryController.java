package com.amazonclone.controllers;

import com.amazonclone.dto.CategoryDto;
import com.amazonclone.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto dto) {
        return categoryService.createCategory(dto);
    }

    @GetMapping
    public List<CategoryDto> getCategories() {
        return categoryService.getAllCategories();
    }
}
