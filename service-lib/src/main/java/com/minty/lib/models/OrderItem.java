package com.minty.lib.models;


import com.minty.lib.enums.OrderItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inv_tbl_order_items")
@DynamicInsert
@Builder
public class OrderItem extends AppModel {

    @Column(nullable = false, updatable = false, unique = true)
    private String orderItemNumber;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(columnDefinition = "varchar(20) default 'PENDING'")
    private OrderItemStatus status = OrderItemStatus.PENDING;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

}
