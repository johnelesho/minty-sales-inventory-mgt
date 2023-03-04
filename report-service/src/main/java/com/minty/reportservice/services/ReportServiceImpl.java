package com.minty.reportservice.services;


import com.minty.lib.mappers.HelpMapper;
import com.minty.lib.utils.KafkaConfigConstant;
import com.minty.reportservice.dto.IOrderReport;
import com.minty.reportservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl {

    final OrderRepository orderRepository;

    final HelpMapper mapper;

    public List<IOrderReport> getOrderReport(LocalDateTime startDate, LocalDateTime endDate) {
        List<IOrderReport> orders = new ArrayList<>();
        orders = orderRepository.generateOrderReportGivenDateRange(startDate, endDate);
        return orders;
    }

    public List<IOrderReport> getOrderReport() {
        List<IOrderReport> orders = new ArrayList<>();
        orders = orderRepository.generateOrderReport();
        return orders;
    }

    @KafkaListener(topics = KafkaConfigConstant.ORDER_TOPIC,
            groupId = KafkaConfigConstant.GROUP_ID)
    public void consume(String message) {
        log.info(String.format("Message received -> %s", message));
    }

}
