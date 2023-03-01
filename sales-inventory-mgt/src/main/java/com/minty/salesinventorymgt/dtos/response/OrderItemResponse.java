package com.minty.salesinventorymgt.dtos.response;

import com.minty.salesinventorymgt.enums.OrderItemStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.minty.salesinventorymgt.models.OrderItem} entity
 */
public record OrderItemResponse(LocalDateTime createdDate, LocalDateTime updatedDate, String orderItemNumber, OrderItemStatus status,
                                Long quantity, BigDecimal totalAmount, BigDecimal unitPrice) implements Serializable {
}