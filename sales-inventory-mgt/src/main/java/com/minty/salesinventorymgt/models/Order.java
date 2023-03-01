package com.minty.salesinventorymgt.models;

import com.minty.salesinventorymgt.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "inv_tbl_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Order extends AppModel {
    @Column(nullable = false, updatable = false, unique = true)
    private String orderNumber;

    @Enumerated(EnumType.STRING)

        private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerInfo customer;

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private Set<OrderItem> orderItems = new LinkedHashSet<>();



}
