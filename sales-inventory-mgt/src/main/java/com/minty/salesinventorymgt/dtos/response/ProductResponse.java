package com.minty.salesinventorymgt.dtos.response;

import com.minty.salesinventorymgt.enums.ProductStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.minty.salesinventorymgt.models.Product} entity
 */
public record ProductResponse(LocalDateTime createdDate, LocalDateTime updatedDate, String name, String code,
                              ProductStatus status,
                              BigDecimal price, BigDecimal generalDiscount, Long allowableMinimumQuantityInStock,
                              Long quantityInStock, String description) implements Serializable {
}