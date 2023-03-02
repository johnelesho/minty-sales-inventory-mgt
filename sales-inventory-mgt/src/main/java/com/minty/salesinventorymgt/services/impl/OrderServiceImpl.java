package com.minty.salesinventorymgt.services.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.minty.lib.dtos.request.OrderRequest;
import com.minty.lib.dtos.response.OrderResponse;
import com.minty.lib.mappers.HelpMapper;
import com.minty.lib.models.CustomerInfo;
import com.minty.lib.models.Order;
import com.minty.lib.utils.TopicConstant;
import com.minty.salesinventorymgt.config.KafkaTemplateConfig;
import com.minty.salesinventorymgt.exceptions.BadRequestException;
import com.minty.salesinventorymgt.exceptions.NotFoundException;
import com.minty.salesinventorymgt.repositories.CustomerInfoRepository;
import com.minty.salesinventorymgt.repositories.OrderRepository;
import com.minty.salesinventorymgt.services.AppService;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements AppService<Order, OrderRequest, OrderResponse> {

    final OrderRepository orderRepository;
    final CustomerInfoRepository customerInfoRepository;
    final HelpMapper helpMapper;
    final KafkaTemplateConfig kafka;
    @Value("${orderNumber.length:7}")
    private int orderNumberLength;
    @Value("${orderPrefix:ORDER}")
    private String orderNumberPrefix;

    @Synchronized
    public OrderResponse placeOrder(OrderRequest request) {
        log.info("Place Order method");
        CustomerInfo customerInfo = null;
        Order order;
        if (request.getCustomer() == null) {
            throw new BadRequestException("Please enter Customer Info");
        } else {
            log.info("Checking if Customer Exist");
            customerInfo = customerInfoRepository.
                    findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase(
                            request.getCustomer().getEmailAddress(),
                            request.getCustomer().getPhoneNumber(),
                            request.getCustomer().getUsername()
                    ).orElse(null);
        }
        try {
            kafka.sendMessage(helpMapper.convertToString(request), TopicConstant.ORDER_TOPIC);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Error with kafka " + e.getMessage());
        }

        try {
            order = helpMapper.convertToOrderEntity(request);
            StringBuilder orderNumber = new StringBuilder(orderNumberLength);
            boolean exist = true;
            String orderNumberSuffice = "";
            while (exist) {
                orderNumber.setLength(0);
                orderNumber.append(orderNumberPrefix);
                orderNumber.append(RandomStringUtils.randomNumeric(orderNumberLength));
                exist = isExist(orderNumber.toString());
            }

            order.setOrderNumber(orderNumber.toString());
        } catch (ParseException e) {
            throw new BadRequestException(e.getMessage());
        }
        if (customerInfo != null) {
            order.setCustomer(customerInfo);
        }
        order = orderRepository.save(order);

        return helpMapper.convertToOrderResonse(order);

    }

    @Override
    public boolean isExist(String key) {
        return orderRepository.existsByOrderNumberIgnoreCase(key);

    }

    @Override
    public OrderResponse findOne(String orderNumber) {
        Order order = findOneEntity(orderNumber);
        return helpMapper.convertToOrderResonse(order);

    }

    @Override
    public OrderResponse addNew(OrderRequest request) {
        return placeOrder(request);
    }

    @Override
    public Order findOneEntity(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber).orElseThrow(() -> new NotFoundException("Order does not exist"));

    }

    @Override
    public List<OrderResponse> findAll(PageRequest pageRequest) {
        return orderRepository.findAll(pageRequest).stream().map(helpMapper::convertToOrderResonse).toList();
    }

    @Override
    @Synchronized
    public OrderResponse updateOne(String uniqueKey, OrderRequest request) {
        Order order = findOneEntity(uniqueKey);
        BeanUtils.copyProperties(request, order);
        return helpMapper.convertToOrderResonse(order);
    }
}
