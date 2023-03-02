package com.minty.lib.dtos.request;


import com.minty.lib.enums.ProductStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class ProductRequest implements Serializable {
    @NotBlank(message = "name must be at least 4 characters")
    @Size(min = 4, message = "name must be at least 4 characters")
    private String name;
    @NotNull
    @PositiveOrZero(message = "price must be a postive number")
    private BigDecimal price;
    @NotNull
    @PositiveOrZero(message = "generalDiscount must be a postive number")
    private BigDecimal generalDiscount;
    @NotNull
    @PositiveOrZero(message = "allowableMinimumQuantityInStock must be a postive number")
    private Long allowableMinimumQuantityInStock;
    @NotNull
    @PositiveOrZero(message = "quantityInStock must be a positive number")
    private Long quantityInStock;
    @NotBlank(message = "description must not be blank")
    private String description;

    @Builder.Default
    private ProductStatus status = ProductStatus.AVAILABLE;
}