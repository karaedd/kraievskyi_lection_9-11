package com.kraievskyi.task.service;

import com.kraievskyi.task.model.Category;
import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();

    Category getCategoryById(Long id);
}
