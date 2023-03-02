package com.minty.lib.models;


import com.minty.lib.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @Column(columnDefinition = "default 'AVAILABLE'")
    private ProductStatus status = ProductStatus.AVAILABLE;

    private BigDecimal generalDiscount;

    @Column(nullable = false, columnDefinition = "integer default 0")

    private Long allowableMinimumQuantityInStock;

    @Column(nullable = false, columnDefinition = "BigInt default 0")
    private Long quantityInStock;

    @Column(columnDefinition = "TEXT")
    private String description;


    @Builder
    public Product(Long id, LocalDateTime createdDate, LocalDateTime updatedDate, String name, String code, BigDecimal price, ProductStatus status, BigDecimal generalDiscount, Long allowableMinimumQuantityInStock, Long quantityInStock, String description) {
        super(id, createdDate, updatedDate);
        this.name = name;
        this.code = code;
        this.price = price;
        this.status = status;
        this.generalDiscount = generalDiscount;
        this.allowableMinimumQuantityInStock = allowableMinimumQuantityInStock;
        this.quantityInStock = quantityInStock;
        this.description = description;
    }
}
