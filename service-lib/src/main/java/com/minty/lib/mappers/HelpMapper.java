package com.minty.lib.mappers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minty.lib.dtos.request.CustomerInfoRequest;
import com.minty.lib.dtos.request.OrderRequest;
import com.minty.lib.dtos.request.ProductRequest;
import com.minty.lib.dtos.response.CustomerInfoResponse;
import com.minty.lib.dtos.response.OrderResponse;
import com.minty.lib.dtos.response.ProductResponse;
import com.minty.lib.enums.OrderStatus;
import com.minty.lib.enums.ProductStatus;
import com.minty.lib.models.CustomerInfo;
import com.minty.lib.models.Order;
import com.minty.lib.models.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@RequiredArgsConstructor
@Slf4j
public class HelpMapper {


    final ModelMapper modelMapper;

    public OrderResponse convertToOrderResonse(Order entity) {
        return modelMapper.map(entity, OrderResponse.class);
    }


    public Order convertToOrderEntity(OrderRequest request) throws ParseException {
        log.info("Request {}", request.toString());
        Order order = modelMapper.map(request, Order.class);
        order.setStatus(OrderStatus.PENDING);

        return order;
    }

    public CustomerInfoResponse convertToCustomerResonse(CustomerInfo entity) {
        return modelMapper.map(entity, CustomerInfoResponse.class);
    }


    public CustomerInfo convertToCustomerEntity(CustomerInfoRequest request) throws ParseException {
        CustomerInfo entity = modelMapper.map(request, CustomerInfo.class);
        return entity;
    }

    public ProductResponse convertToProductResonse(Product entity) {
        return modelMapper.map(entity, ProductResponse.class);
    }


    public Product convertToProductEntity(ProductRequest request) throws ParseException {

        Product entity = modelMapper.map(request, Product.class);
        entity.setStatus(ProductStatus.AVAILABLE);
        return entity;
    }

    public String convertToString(Object request) throws ParseException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String plainJson = objectMapper.writeValueAsString(request);
        return plainJson;
    }
}
