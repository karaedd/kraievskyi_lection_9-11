package com.kraievskyi.task.service;

import com.kraievskyi.task.model.Product;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface ProductService {
    Product create(Product product);

    List<Product> getAllProducts();

    Product getById(Long id);

    Product updateProduct(Long id, Product product);

    void deleteById(Long id);

    List<Product> findAllByCategoryIdAndName(Long id, String name, PageRequest pageRequest);

    void updateProductName(Long id, String name);
}
