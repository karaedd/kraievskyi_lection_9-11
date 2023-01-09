package com.kraievskyi.task.service;

import com.kraievskyi.task.model.Category;
import java.util.List;

public interface CategoryService {
    Category create(Category category);

    List<Category> getAllCategory();

    Category getCategoryById(Long id);

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long id);
}
