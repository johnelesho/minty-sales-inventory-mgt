package com.minty.salesinventorymgt.models;

import com.minty.salesinventorymgt.enums.OrderItemStatus;
import com.minty.salesinventorymgt.enums.ProductStatus;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;

@Entity
@Table(name = "inv_tbl_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert

public class Product extends AppModel {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private BigDecimal price;

@Enumerated(EnumType.STRING)
    private ProductStatus status;

    private BigDecimal generalDiscount;

    @Column(nullable = false, columnDefinition = "integer default 0")

    private Long allowableMinimumQuantityInStock;

    @Column(nullable = false, columnDefinition = "BigInt default 0")
    private Long quantityInStock;

    @Column(columnDefinition = "TEXT")
    private String description;


}
