package com.minty.salesinventorymgt.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.minty.salesinventorymgt.models.Product} entity
 */
public record ProductRequest(
        @NotBlank
        String name,
        @NotNull
                @PositiveOrZero
        BigDecimal price,
        @NotNull
                @PositiveOrZero
        BigDecimal generalDiscount,
        @NotNull
        @PositiveOrZero
                         Long allowableMinimumQuantityInStock,
        @NotNull
        @PositiveOrZero
        Long quantityInStock,
        @NotBlank
                         String description) implements Serializable {
}