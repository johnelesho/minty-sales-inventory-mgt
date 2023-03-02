package com.minty.lib.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerInfoResponse implements Serializable {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String fullName;

    private String username;

    private String phoneNumber;
    private String address;
    private String emailAddress;
}