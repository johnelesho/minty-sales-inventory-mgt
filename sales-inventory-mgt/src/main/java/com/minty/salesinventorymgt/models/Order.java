package com.minty.salesinventorymgt.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(schema = "inv_tbl_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AppModel {
    @Column(nullable = false, updatable = false, unique = true)
    private String orderNumber;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerInfo customer;

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

}
