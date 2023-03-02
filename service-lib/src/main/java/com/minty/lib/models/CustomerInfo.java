package com.minty.lib.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inv_tbl_customer")
public class CustomerInfo extends AppModel {
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String address;

    @Column(nullable = false, unique = true)
    private String emailAddress;

}
