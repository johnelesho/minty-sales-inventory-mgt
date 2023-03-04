package com.minty.salesinventorymgt.repositories;


import com.minty.lib.enums.OrderStatus;
import com.minty.lib.enums.ProductStatus;
import com.minty.lib.models.Order;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByOrderNumberIgnoreCase(String orderNumber);

    Optional<Order> findByOrderNumber(String orderNumber);

    List<Order> findAllByCreatedDateBetweenOrderByCreatedDateDesc(LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findAllByOrderByCreatedDateDesc();


    List<Order> findAllByStatusOrderByCreatedDateDesc(OrderStatus status);
}