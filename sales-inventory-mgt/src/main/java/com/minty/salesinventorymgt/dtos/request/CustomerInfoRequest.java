package com.minty.salesinventorymgt.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * A DTO for the {@link com.minty.salesinventorymgt.models.CustomerInfo} entity
 */
public record CustomerInfoRequest(String fullName, String phoneNumber, String address,

                                  String emailAddress) implements Serializable {
}