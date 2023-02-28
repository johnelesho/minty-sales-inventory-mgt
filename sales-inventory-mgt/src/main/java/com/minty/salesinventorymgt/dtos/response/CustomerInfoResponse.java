package com.minty.salesinventorymgt.dtos.response;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.minty.salesinventorymgt.models.CustomerInfo} entity
 */
public record CustomerInfoResponse(LocalDateTime createdDate, LocalDateTime updatedDate, String fullName,
                                   String phoneNumber, String address, String emailAddress) implements Serializable {
}