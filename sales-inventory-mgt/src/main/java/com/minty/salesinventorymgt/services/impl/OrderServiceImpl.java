package com.minty.salesinventorymgt.services.impl;

import com.minty.salesinventorymgt.HelpMapper;
import com.minty.salesinventorymgt.dtos.request.OrderRequest;
import com.minty.salesinventorymgt.dtos.response.OrderResponse;
import com.minty.salesinventorymgt.exceptions.BadRequestException;
import com.minty.salesinventorymgt.exceptions.NotFoundException;
import com.minty.salesinventorymgt.models.Order;
import com.minty.salesinventorymgt.repositories.OrderRepository;
import com.minty.salesinventorymgt.services.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements AppService<Order,OrderRequest, OrderResponse> {

    final OrderRepository orderRepository;

    @Value("${orderNumber.length:11}")
    private int orderNumberLength;
    HelpMapper helpMapper;

    public OrderResponse placeOrder(OrderRequest request){
      log.info("Place Order method");
        if(request.customer() != null && request.customer().emailAddress()!= null){
            log.info("Checking if Customer Exist");
        }
        Order order;
        try {
            order = helpMapper.convertToOrderEntity(request);
            StringBuilder orderNumber = new StringBuilder(orderNumberLength);
            boolean exist = true;
            while (exist){
                orderNumber.append(RandomStringUtils.randomAlphanumeric(orderNumberLength));
                exist = isExist(orderNumber.toString());
            }

            order.setOrderNumber(orderNumber.toString());
        } catch (ParseException e) {
            throw new BadRequestException(e.getMessage());
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
    public OrderResponse updateOne(String uniqueKey, OrderRequest request) {
        Order order = findOneEntity(uniqueKey);
        BeanUtils.copyProperties(request, order);
        return helpMapper.convertToOrderResonse(order);
    }
}
