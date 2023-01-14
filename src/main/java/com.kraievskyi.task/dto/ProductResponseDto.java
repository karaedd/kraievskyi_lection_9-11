package com.kraievskyi.task.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductResponseDto {
    private Long id;
    private String name;
    private Double price;
    private Long categoryId;
    private LocalDate dateManufacture;
    private LocalDate dateExpire;
}
