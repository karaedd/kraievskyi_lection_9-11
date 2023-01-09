package com.kraievskyi.task.dto.mapper;

import com.kraievskyi.task.dto.ProductRequestDto;
import com.kraievskyi.task.dto.ProductResponseDto;
import com.kraievskyi.task.model.Product;
import com.kraievskyi.task.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ProductResponseDto toProductResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategoryId(product.getCategory().getId());
        productResponseDto.setDateManufacture(product.getDateManufacture());
        productResponseDto.setDateExpire(product.getDateExpire());
        return productResponseDto;
    }

    public Product toModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(categoryService.getCategoryById(productRequestDto.getCategoryId()));
        product.setDateManufacture(productRequestDto.getDateManufacture());
        product.setDateExpire(productRequestDto.getDateExpire());
        return product;
    }
}
