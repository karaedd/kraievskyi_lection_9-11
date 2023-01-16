package com.kraievskyi.task.service.impl;

import com.kraievskyi.task.model.Product;
import com.kraievskyi.task.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void shouldUpdateProductName() {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(new Product("milk")));
        Product actual = productService.getById(1L);
        Assertions.assertEquals("milk", actual.getName());
    }
}
