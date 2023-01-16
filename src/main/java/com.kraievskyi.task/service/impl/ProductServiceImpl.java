package com.kraievskyi.task.service.impl;

import com.kraievskyi.task.model.Product;
import com.kraievskyi.task.repository.ProductRepository;
import com.kraievskyi.task.service.ProductService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productToChange = productRepository.findById(id).orElseThrow();
        productToChange.setName(product.getName());
        productToChange.setPrice(product.getPrice());
        productToChange.setCategory(product.getCategory());
        productToChange.setDateManufacture(product.getDateManufacture());
        productToChange.setDateExpire(product.getDateExpire());
        productRepository.save(productToChange);
        return productToChange;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByCategoryIdAndName(Long id, String name, PageRequest pageRequest) {
        return productRepository.findAllByCategoryIdAndName(id, name, pageRequest);
    }

    @Transactional
    @Override
    public void updateProductName(Long id, String name) {
        productRepository.updateProductName(id, name);
    }
}
