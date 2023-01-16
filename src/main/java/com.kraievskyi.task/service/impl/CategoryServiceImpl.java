package com.kraievskyi.task.service.impl;

import com.kraievskyi.task.model.Category;
import com.kraievskyi.task.repository.CategoryRepository;
import com.kraievskyi.task.service.CategoryService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }
}
