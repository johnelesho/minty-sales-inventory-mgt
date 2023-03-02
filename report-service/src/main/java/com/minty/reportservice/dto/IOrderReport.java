package com.minty.reportservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


public interface IOrderReport {
    LocalDate getDate();

    Long getTotalOrder();

    BigDecimal getTotalOrderAmount();
}
