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
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category categoryToChange = categoryRepository.findById(id).orElseThrow();
        categoryToChange.setName(category.getName());
        categoryToChange.setProduct(category.getProduct());
        return categoryToChange;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
