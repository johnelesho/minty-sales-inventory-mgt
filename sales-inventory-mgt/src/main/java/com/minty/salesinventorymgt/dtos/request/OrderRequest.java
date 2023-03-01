package com.minty.salesinventorymgt.dtos.request;

import com.minty.salesinventorymgt.enums.OrderStatus;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.minty.salesinventorymgt.models.Order} entity
 */
@Validated

public record OrderRequest(

       @NotNull
       CustomerInfoRequest customer,
                       @NotNull
                       Set<OrderItemRequest> orderItems) implements Serializable {
}