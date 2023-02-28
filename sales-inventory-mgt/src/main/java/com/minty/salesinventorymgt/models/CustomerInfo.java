package com.minty.salesinventorymgt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "inv_tbl_customer")
public class CustomerInfo extends AppModel{
    private String fullName;
    @Column(nullable = false,unique = true)
    private String phoneNumber;

    private String address;

    @Column(nullable = false, unique = true)
    private String emailAddress;

}
