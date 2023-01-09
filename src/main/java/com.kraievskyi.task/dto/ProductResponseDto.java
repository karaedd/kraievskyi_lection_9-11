package com.kraievskyi.task.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private Double price;
    private Long categoryId;
    private LocalDate dateManufacture;
    private LocalDate dateExpire;
}
