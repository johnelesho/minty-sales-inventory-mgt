package com.minty.salesinventorymgt.utils;


import com.minty.lib.enums.ProductStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProductStatusConverter implements AttributeConverter<ProductStatus, String> {

    @Override
    public String convertToDatabaseColumn(ProductStatus status) {
        if (status == null) {
            return null;
        }
        return status.name();
    }

    @Override
    public ProductStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(ProductStatus.values())
                .filter(c -> c.name().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}