package com.minty.lib.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerInfoRequest implements Serializable {
    private String fullName;
    private String phoneNumber;
    private String address;
    private String username;


    private String emailAddress;
}