package com.kraievskyi.task.repository;

import com.kraievskyi.task.model.Product;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryIdAndName(Long id, String name, PageRequest pageRequest);

    @Modifying
    @Query("update Product p set p.name = :name where p.id = :id")
    void updateProductName(@Param("id") Long id, @Param("name") String name);
}
