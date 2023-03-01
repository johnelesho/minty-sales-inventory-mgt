package com.minty.salesinventorymgt.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.minty.salesinventorymgt.models.Product} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest implements Serializable{
        @NotBlank
        String name;
        @NotNull
                @PositiveOrZero
        BigDecimal price;
        @NotNull
                @PositiveOrZero
        BigDecimal generalDiscount;
        @NotNull
        @PositiveOrZero
                         Long allowableMinimumQuantityInStock;
        @NotNull
        @PositiveOrZero
        Long quantityInStock;
        @NotBlank
                         String description;
}