package com.minty.salesinventorymgt;

import com.minty.salesinventorymgt.dtos.request.ProductRequest;
import com.minty.salesinventorymgt.dtos.response.ProductResponse;
import com.minty.salesinventorymgt.enums.ProductStatus;
import com.minty.salesinventorymgt.models.Product;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

public class Builders {
    public static ProductRequest buildProductRequest() {
        return ProductRequest.builder()
                .name("ProductTest One")
                .price(BigDecimal.valueOf(450))
                .description("A Simple Product for Test")
                .generalDiscount(BigDecimal.TEN)
                .quantityInStock(20l)
                .build();
    }


    public static ProductResponse buildProductResponse() {
        return ProductResponse.builder()
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .name("ProductTest One")
                .code("prod6783")
                .status(ProductStatus.AVAILABLE)
                .price(BigDecimal.valueOf(450))
                .generalDiscount(BigDecimal.valueOf(82.74))
                .allowableMinimumQuantityInStock(0L)
                .quantityInStock(20L)
                .description("Product One for Test")
                .build();
    }

    public static Product buildProduct(String name, ProductStatus status) {
        return Product.builder()
                .id(1L)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .name(name)
                .code(RandomStringUtils.randomAlphanumeric(7))
                .status(ProductStatus.AVAILABLE)
                .price(BigDecimal.valueOf(Long.parseLong(RandomStringUtils.randomNumeric(3))))
                .generalDiscount(BigDecimal.valueOf(Long.parseLong(RandomStringUtils.randomNumeric(3))))
                .allowableMinimumQuantityInStock(new Random().nextLong())
                .quantityInStock(new Random().nextLong())
                .description(name + " " + RandomStringUtils.randomAlphabetic(10))
                .build();
    }

    public static Product buildProduct(String code, String name, ProductStatus status) {
        return Product.builder()
                .id(1L)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .name(name)
                .code(code)
                .status(ProductStatus.AVAILABLE)
                .price(BigDecimal.valueOf(Long.parseLong(RandomStringUtils.randomNumeric(3))))
                .generalDiscount(BigDecimal.valueOf(Long.parseLong(RandomStringUtils.randomNumeric(3))))
                .allowableMinimumQuantityInStock(new Random().nextLong())
                .quantityInStock(new Random().nextLong())
                .description(name + " " + RandomStringUtils.randomAlphabetic(10))
                .build();
    }
}
