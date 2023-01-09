package com.kraievskyi.task.repository;

import com.kraievskyi.task.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
