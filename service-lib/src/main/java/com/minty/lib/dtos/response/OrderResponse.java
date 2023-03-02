package com.minty.lib.dtos.response;


import com.minty.lib.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse implements Serializable {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String orderNumber;

    private BigDecimal totalOrderAmount;
    private OrderStatus status;

    private CustomerInfoResponse customer;
    private Set<OrderItemResponse> orderItems;
}