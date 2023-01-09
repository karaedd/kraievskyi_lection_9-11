package com.kraievskyi.task.controller;

import com.kraievskyi.task.dto.ProductRequestDto;
import com.kraievskyi.task.dto.ProductResponseDto;
import com.kraievskyi.task.dto.mapper.ProductMapper;
import com.kraievskyi.task.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping()
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        return productMapper.toProductResponseDto(productService
                .create(productMapper.toModel(productRequestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.toProductResponseDto(productService.getById(id));
    }

    @GetMapping
    public List<ProductResponseDto> getAll() {
        return productService.getAllProducts()
                .stream()
                .map(productMapper::toProductResponseDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        return productMapper.toProductResponseDto(productService
                .updateProduct(id, productMapper.toModel(productRequestDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }
}