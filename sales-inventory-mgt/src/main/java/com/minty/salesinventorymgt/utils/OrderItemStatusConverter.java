package com.minty.salesinventorymgt.utils;


import com.minty.lib.enums.OrderItemStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class OrderItemStatusConverter implements AttributeConverter<OrderItemStatus, String> {

    @Override
    public String convertToDatabaseColumn(OrderItemStatus status) {
        if (status == null) {
            return null;
        }
        return status.name();
    }

    @Override
    public OrderItemStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(OrderItemStatus.values())
                .filter(c -> c.name().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}