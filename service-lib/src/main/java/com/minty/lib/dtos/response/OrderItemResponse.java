package com.minty.lib.dtos.response;


import com.minty.lib.enums.OrderItemStatus;
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
public class OrderItemResponse implements Serializable {
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
    private String orderItemNumber;
    private OrderItemStatus status;

    private Long quantity;
    private BigDecimal totalAmount;
    private BigDecimal unitPrice;
}