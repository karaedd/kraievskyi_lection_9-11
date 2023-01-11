package com.kraievskyi.task.dto;

import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ProductRequestDto {
    private String name;
    private Double price;
    private Long categoryId;
    private LocalDate dateManufacture;
    private LocalDate dateExpire;
}
