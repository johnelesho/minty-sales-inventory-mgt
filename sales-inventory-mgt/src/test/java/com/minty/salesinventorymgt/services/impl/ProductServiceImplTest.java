package com.minty.salesinventorymgt.services.impl;

import com.minty.salesinventorymgt.dtos.response.ProductResponse;
import com.minty.salesinventorymgt.enums.ProductStatus;
import com.minty.salesinventorymgt.mappers.HelpMapper;
import com.minty.salesinventorymgt.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import java.util.Optional;

import static com.minty.salesinventorymgt.Builders.buildProduct;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @InjectMocks
    HelpMapper helpMapper;

    @Mock
    ProductRepository productRepository;

    @Test
    void isExist() {
    }

    @Test
    void isExistByName() {
    }

    @Test
    void findOneEntity() {
    }

    @Test
    void findOne() {
        String uniqueKey = RandomStringUtils.randomAlphanumeric(7);
        when(productRepository.findByCodeIgnoreCase(uniqueKey))
                .thenReturn(Optional.ofNullable(buildProduct(uniqueKey, "Product 1", ProductStatus.AVAILABLE)));

        ProductResponse one = productService.findOne(uniqueKey);

        verify(productRepository, times(1)).findByCodeIgnoreCase(uniqueKey);
    }

    @Test
    void addNew() {
    }

    @Test
    void findAll() {
    }

    @Test
    void updateOne() {
    }
}