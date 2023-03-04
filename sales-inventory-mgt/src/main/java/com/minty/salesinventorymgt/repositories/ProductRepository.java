package com.minty.salesinventorymgt.repositories;


import com.minty.lib.dtos.response.ProductResponse;
import com.minty.lib.enums.ProductStatus;
import com.minty.lib.models.Product;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCodeIgnoreCase(String uniqueKey);

    Optional<Product> findByCodeIgnoreCase(String uniqueKey);
    Optional<Product> findByCodeIgnoreCaseAndStatus(String uniqueKey, ProductStatus status);

    boolean existsByNameIgnoreCase(String name);

    Product findByNameIgnoreCase(String name);

    List<Product> findAllByStatusOrNameContainingIgnoreCase(ProductStatus status, String name);


}