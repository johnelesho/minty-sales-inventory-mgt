package com.minty.lib.dtos.response;


import com.minty.lib.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse implements Serializable {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String name;
    private String code;

    private ProductStatus status;

    private BigDecimal price;
    private BigDecimal generalDiscount;
    private Long allowableMinimumQuantityInStock;

    private Long quantityInStock;
    private String description;
}