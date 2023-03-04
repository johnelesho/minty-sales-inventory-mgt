package com.minty.lib.dtos.request;

import com.minty.lib.enums.OrderItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class OrderItemRequest implements Serializable {
    @NotNull
    @Positive
    private Long quantity;

    @NotNull
    @NotBlank
    private String product;
    @NotNull
    @PositiveOrZero
    private BigDecimal totalAmount;
    @NotNull
    @PositiveOrZero
    private BigDecimal unitPrice;

    @Builder.Default
    private OrderItemStatus status = OrderItemStatus.PENDING;


}