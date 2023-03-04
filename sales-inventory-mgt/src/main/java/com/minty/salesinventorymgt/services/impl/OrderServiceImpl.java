package com.minty.salesinventorymgt.services.impl;


import com.minty.lib.dtos.request.OrderItemRequest;
import com.minty.lib.dtos.request.OrderRequest;
import com.minty.lib.dtos.response.OrderResponse;
import com.minty.lib.enums.ProductStatus;
import com.minty.lib.mappers.HelpMapper;
import com.minty.lib.models.CustomerInfo;
import com.minty.lib.models.Order;
import com.minty.lib.models.OrderItem;
import com.minty.lib.models.Product;
import com.minty.lib.utils.KafkaConfigConstant;
import com.minty.salesinventorymgt.config.KafkaTemplateConfig;
import com.minty.salesinventorymgt.exceptions.BadRequestException;
import com.minty.salesinventorymgt.exceptions.NotFoundException;
import com.minty.salesinventorymgt.repositories.CustomerInfoRepository;
import com.minty.salesinventorymgt.repositories.OrderItemRepository;
import com.minty.salesinventorymgt.repositories.OrderRepository;
import com.minty.salesinventorymgt.repositories.ProductRepository;
import com.minty.salesinventorymgt.services.AppService;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements AppService<Order, OrderRequest, OrderResponse> {

    final OrderRepository orderRepository;
    final OrderItemRepository orderItemRepository;
    final CustomerInfoRepository customerInfoRepository;

    final ProductRepository productRepository;
    final HelpMapper helpMapper;
    final KafkaTemplateConfig kafka;
    @Value("${orderNumber.length:5}")
    private int orderNumberLength;
    @Value("${orderPrefix:ORDER}")
    private String orderNumberPrefix;

    @Synchronized
    public OrderResponse placeOrder(OrderRequest request) {
        log.info("Place Order method");
        CustomerInfo customerInfo = null;
        Order order;
        if (request == null || request.getCustomer() == null) {
            throw new BadRequestException("Please enter Customer Info");
        }
        else {
            log.info("Checking if Customer Exist");
            customerInfo = customerInfoRepository.
                    findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase(
                            request.getCustomer().getEmailAddress(),
                            request.getCustomer().getPhoneNumber(),
                            request.getCustomer().getUsername()
                    ).orElse(null);
        }
        try {
            kafka.sendMessage(helpMapper.convertToString(request), KafkaConfigConstant.ORDER_TOPIC);
            StringBuilder orderNumber = new StringBuilder(orderNumberLength);
            boolean exist = true;
            while (exist) {
                orderNumber.setLength(0);
                orderNumber.append(orderNumberPrefix);
                orderNumber.append(RandomStringUtils.randomNumeric(orderNumberLength));
                exist = isExist(orderNumber.toString());
            }

//            Order Number for each order
            List<OrderItemRequest> orderItems = request.getOrderItems().stream().toList();
            Set<OrderItem> items = new LinkedHashSet<>();
            for (OrderItemRequest orderItem : orderItems
            ) {
                String productCode = orderItem.getProduct();
                if(productCode== null || productCode.isEmpty()){
                    throw new BadRequestException("Product Can not be null");
                }
                Product product = productRepository.findByCodeIgnoreCase(productCode).orElseThrow(() ->
                        new NotFoundException(String.format("Product - %s Not Found", productCode)));
                if(product.getStatus() == ProductStatus.NOT_AVAILABLE){
                    throw new BadRequestException(String.format("Product - %s is not currently available", product.getCode()));
                }
                OrderItem item = helpMapper.convertToOrderItemEntity(orderItem);
                item.setProduct(product);
                item.setOrderItemNumber(generateOrderItem(orderNumber.toString(), orderItems.indexOf(orderItem)));

                items.add(item);
            }
            order = helpMapper.convertToOrderEntity(request);
            order.setOrderNumber(orderNumber.toString());
            order.setOrderItems(items);


            if (customerInfo != null) {
                order.setCustomer(customerInfo);
            }
            log.info("{} - {}", order.getOrderNumber() );
            order = orderRepository.save(order);
            return helpMapper.convertToOrderResonse(order);

        }catch (Exception e) {
            log.error("Error In Placing Order", e);
            throw new BadRequestException(e.getMessage());
        }


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
        Page<Order> orders;
        if (pageRequest != null) {
            orders = orderRepository.findAll(pageRequest);
            log.info(orders.toString());
            if(orders != null)
                return orders.stream().map(helpMapper::convertToOrderResonse).toList();
        }
        return new ArrayList<>();
    }

    @Override
    @Synchronized
    public OrderResponse updateOne(String uniqueKey, OrderRequest request) {
        if (request == null) {
            throw new BadRequestException("Request cannot be null");
        }
        Order order = findOneEntity(uniqueKey);
        if (order == null) {
            throw new NotFoundException("Order not found");
        }
        BeanUtils.copyProperties(request, order);
        return helpMapper.convertToOrderResonse(order);
    }


    private String generateOrderItem(String num, int index) {
        return num + "__" + index;

    }
    }
