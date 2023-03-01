package com.minty.salesinventorymgt.models;

import com.minty.salesinventorymgt.enums.OrderItemStatus;
import com.minty.salesinventorymgt.enums.OrderStatus;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inv_tbl_order_items")
@DynamicInsert
public class OrderItem extends AppModel {

    @Column(nullable = false, updatable = false, unique = true)
    private String orderItemNumber;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    private Long quantity;

    private BigDecimal totalAmount;

    private BigDecimal unitPrice;

    @Enumerated(EnumType.STRING)
    private OrderItemStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

}
