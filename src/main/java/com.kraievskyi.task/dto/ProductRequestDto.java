package com.kraievskyi.task.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    @NotBlank
    private String name;
    @NotNull
    @Min(0)
    private Double price;
    private Long categoryId;
    private LocalDate dateManufacture;
    private LocalDate dateExpire;
}
