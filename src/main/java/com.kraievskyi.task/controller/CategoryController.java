package com.kraievskyi.task.controller;

import com.kraievskyi.task.dto.CategoryResponseDto;
import com.kraievskyi.task.dto.mapper.CategoryMapper;
import com.kraievskyi.task.service.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public List<CategoryResponseDto> getAll() {
        return categoryService.getAllCategory()
                .stream()
                .map(categoryMapper::toCategoryResponseDto)
                .collect(Collectors.toList());
    }
}
