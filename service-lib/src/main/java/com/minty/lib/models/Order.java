package com.minty.lib.models;


import com.minty.lib.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "inv_tbl_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Builder
public class Order extends AppModel {
    @Column(nullable = false, updatable = false, unique = true)
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(columnDefinition = "varchar(20) default 'PENDING'")
    private OrderStatus status = OrderStatus.PENDING;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private CustomerInfo customer;

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

    @Column(nullable = false)
    private BigDecimal totalOrderAmount;


}
