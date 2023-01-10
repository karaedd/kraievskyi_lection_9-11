package com.kraievskyi.task.dto.mapper;

import com.kraievskyi.task.dto.CategoryResponseDto;
import com.kraievskyi.task.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponseDto toCategoryResponseDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }
}
