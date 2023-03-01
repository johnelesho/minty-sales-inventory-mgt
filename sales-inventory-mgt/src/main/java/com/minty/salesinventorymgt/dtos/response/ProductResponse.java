package com.minty.salesinventorymgt.dtos.response;

import com.minty.salesinventorymgt.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.minty.salesinventorymgt.models.Product} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse implements  Serializable{
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
    String name;
    String code;

                              ProductStatus status;

                              BigDecimal price;
                              BigDecimal generalDiscount;
                              Long allowableMinimumQuantityInStock;

                              Long quantityInStock;
                              String description;
}