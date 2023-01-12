package com.kraievskyi.task.dto;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String name;
    private Double price;
    private Long categoryId;
    private LocalDate dateManufacture;
    private LocalDate dateExpire;
}
