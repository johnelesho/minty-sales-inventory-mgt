package com.minty.reportservice.repositories;


import com.minty.lib.models.Order;
import com.minty.reportservice.dto.IOrderReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT DATE(o.created_date) as date, COUNT(o.id) as totalOrder, sum(o.total_order_amount) as totalOrderAmount  "
            + "FROM inv_tbl_order AS o GROUP BY DATE(o.created_date) ORDER BY DATE(o.created_date) DESC", nativeQuery = true)
    List<IOrderReport> generateOrderReport();

    @Query(value = "SELECT DATE(o.created_date) as date, COUNT(o.id) as totalOrder," +
            " sum(o.total_order_amount) as totalOrderAmount  "
            + "FROM inv_tbl_order AS o WHERE" +
            " (o.created_date <= :endDate or o.created_date >=:startDate)" +
            " GROUP BY DATE(o.created_date) ORDER BY DATE(o.created_date) DESC", nativeQuery = true)
    List<IOrderReport> generateOrderReportGivenDateRange(LocalDateTime startDate, LocalDateTime endDate);
}