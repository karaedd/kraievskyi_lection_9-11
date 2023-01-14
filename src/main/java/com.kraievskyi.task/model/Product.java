package com.kraievskyi.task.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    @ManyToOne
    private Category category;
    private LocalDate dateManufacture;
    private LocalDate dateExpire;

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, Double price, Category category,
                   LocalDate dateManufacture, LocalDate dateExpire) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.dateManufacture = dateManufacture;
        this.dateExpire = dateExpire;
    }

    public Product(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
