package com.minty.salesinventorymgt;

import com.minty.salesinventorymgt.dtos.request.CustomerInfoRequest;
import com.minty.salesinventorymgt.dtos.request.OrderRequest;
import com.minty.salesinventorymgt.dtos.request.ProductRequest;
import com.minty.salesinventorymgt.dtos.response.CustomerInfoResponse;
import com.minty.salesinventorymgt.dtos.response.OrderResponse;
import com.minty.salesinventorymgt.dtos.response.ProductResponse;
import com.minty.salesinventorymgt.enums.OrderStatus;
import com.minty.salesinventorymgt.models.CustomerInfo;
import com.minty.salesinventorymgt.models.Order;
import com.minty.salesinventorymgt.models.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
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
        log.info("Dto {}", request.toString());
        Product entity = modelMapper.map(request, Product.class);
//        Product entity = new Product();
//        BeanUtils.copyProperties(request, entity);
        log.info("Entity {}", entity);
       return entity;
    }
}
