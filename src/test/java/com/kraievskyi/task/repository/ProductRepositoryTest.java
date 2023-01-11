package com.kraievskyi.task.repository;

import com.kraievskyi.task.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldUpdateProductName() {
        productRepository.updateProductName(1L, "milk");
        Product actual = productRepository.findById(1L).orElseThrow();
        Assertions.assertEquals(actual.getName(), "milk");
    }
}
