package com.amazonclone.services;

import com.amazonclone.dto.CategoryDto;
import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto dto);
    List<CategoryDto> getAllCategories();
}
