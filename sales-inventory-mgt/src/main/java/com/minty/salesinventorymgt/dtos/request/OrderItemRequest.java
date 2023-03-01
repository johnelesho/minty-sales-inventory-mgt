package com.minty.salesinventorymgt.dtos.request;

import com.minty.salesinventorymgt.enums.OrderItemStatus;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.minty.salesinventorymgt.models.OrderItem} entity
 */
public record OrderItemRequest(
        @NotNull
                @Positive
        Long quantity,
        @NotNull
        @PositiveOrZero
        BigDecimal totalAmount,
        @NotNull
        @PositiveOrZero
        BigDecimal unitPrice
        ) implements Serializable {
}