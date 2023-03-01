package com.minty.salesinventorymgt.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inv_tbl_customer" )
public class CustomerInfo extends AppModel{
    private String fullName;
    @Column(nullable = false,unique = true)
    private String phoneNumber;

    private String address;

    @Column(nullable = false, unique = true)
    private String emailAddress;

}
