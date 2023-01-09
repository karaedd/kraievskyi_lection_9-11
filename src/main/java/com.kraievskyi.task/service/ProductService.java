package com.kraievskyi.task.service;

import com.kraievskyi.task.model.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);

    List<Product> getAllProducts();

    Product getById(Long id);

    Product updateProduct(Long id, Product product);

    void deleteById(Long id);
}
