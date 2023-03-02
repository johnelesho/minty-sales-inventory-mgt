package com.minty.lib.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemRequest implements Serializable {
    @NotNull
    @Positive
    private Long quantity;
    @NotNull
    @PositiveOrZero
    private BigDecimal totalAmount;
    @NotNull
    @PositiveOrZero
    private BigDecimal unitPrice;

}