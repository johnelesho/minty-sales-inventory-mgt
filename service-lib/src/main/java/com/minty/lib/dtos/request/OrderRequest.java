package com.minty.lib.dtos.request;


import com.minty.lib.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;


@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest implements Serializable {

    @NotNull(message = "customer cannot be null")
    private CustomerInfoRequest customer;
    @NotNull(message = "orderItems cannot be null")
    private Set<OrderItemRequest> orderItems;

    @NotNull(message = "totalOrderAmount cannot be null")
    private BigDecimal totalOrderAmount;

    @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;
}