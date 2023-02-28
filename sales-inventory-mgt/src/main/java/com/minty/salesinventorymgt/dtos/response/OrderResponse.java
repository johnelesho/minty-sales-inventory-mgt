package com.minty.salesinventorymgt.dtos.response;

import com.minty.salesinventorymgt.enums.OrderItemStatus;
import com.minty.salesinventorymgt.enums.OrderStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * A DTO for the {@link com.minty.salesinventorymgt.models.Order} entity
 */
public record OrderResponse(LocalDateTime createdDate, LocalDateTime updatedDate, String orderNumber,
                            OrderStatus orderStatus,
                            CustomerInfoResponse customer, Set<OrderItemResponse> orderItems) implements Serializable {
}