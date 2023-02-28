package com.minty.salesinventorymgt.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(schema = "inv_tbl_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AppModel {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private BigDecimal price;


    private BigDecimal generalDiscount;

    @Column(nullable = false, columnDefinition = "Default 0")

    private Long allowableMinimumQuantityInStock;

    @Column(nullable = false, columnDefinition = "Default 0")
    private Long quantityInStock;

    @Column(columnDefinition = "TEXT")
    private String description;


}
