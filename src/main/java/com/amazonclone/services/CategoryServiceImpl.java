package com.amazonclone.services;

import com.amazonclone.dto.CategoryDto;
import com.amazonclone.models.Category;
import com.amazonclone.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();

        Category saved = categoryRepository.save(category);

        return CategoryDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .description(saved.getDescription())
                .build();
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(cat ->
                CategoryDto.builder()
                        .id(cat.getId())
                        .name(cat.getName())
                        .description(cat.getDescription())
                        .build()
        ).collect(Collectors.toList());
    }
}
