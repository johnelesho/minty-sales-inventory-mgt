package com.minty.salesinventorymgt.repositories;


import com.minty.lib.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCodeIgnoreCase(String uniqueKey);

    Optional<Product> findByCodeIgnoreCase(String uniqueKey);

    boolean existsByNameIgnoreCase(String name);

    Product findByNameIgnoreCase(String name);
}