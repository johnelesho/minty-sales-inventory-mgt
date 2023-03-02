package com.minty.salesinventorymgt.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.minty.lib.dtos.request.OrderRequest;
import com.minty.lib.dtos.response.ApiResponse;
import com.minty.lib.dtos.response.CustomerInfoResponse;
import com.minty.lib.dtos.response.OrderResponse;
import com.minty.lib.enums.OrderStatus;
import com.minty.lib.mappers.HelpMapper;
import com.minty.lib.models.CustomerInfo;
import com.minty.lib.models.Order;
import com.minty.salesinventorymgt.config.KafkaTemplateConfig;
import com.minty.salesinventorymgt.repositories.CustomerInfoRepository;
import com.minty.salesinventorymgt.repositories.OrderRepository;
import com.minty.salesinventorymgt.services.AppService;
import com.minty.salesinventorymgt.services.impl.OrderServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class OrderControllerTest {
    /**
     * Method under test: {@link OrderController#createNew(OrderRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNew() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.minty.salesinventorymgt.exceptions.BadRequestException: Please enter Customer Info
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.placeOrder(OrderServiceImpl.java:51)
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.addNew(OrderServiceImpl.java:109)
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.addNew(OrderServiceImpl.java:30)
        //       at com.minty.salesinventorymgt.controllers.OrderController.createNew(OrderController.java:28)
        //   See https://diff.blue/R013 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        CustomerInfoRepository customerInfoRepository = mock(CustomerInfoRepository.class);
        OrderController orderController = new OrderController(new OrderServiceImpl(orderRepository, customerInfoRepository,
                new HelpMapper(new ModelMapper()), mock(KafkaTemplateConfig.class)));
        orderController.createNew(new OrderRequest());
    }

    /**
     * Method under test: {@link OrderController#createNew(OrderRequest)}
     */
    @Test
    void testCreateNew2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AppService<Order, OrderRequest, OrderResponse> appService = (AppService<Order, OrderRequest, OrderResponse>) mock(
                AppService.class);
        OrderResponse orderResponse = new OrderResponse();
        when(appService.addNew((OrderRequest) any())).thenReturn(orderResponse);
        OrderController orderController = new OrderController(appService);
        ResponseEntity<ApiResponse> actualCreateNewResult = orderController.createNew(new OrderRequest());
        assertTrue(actualCreateNewResult.hasBody());
        assertTrue(actualCreateNewResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.CREATED, actualCreateNewResult.getStatusCode());
        ApiResponse body = actualCreateNewResult.getBody();
        assertSame(orderResponse, body.getData());
        assertEquals(HttpStatus.CREATED, body.getStatus());
        assertEquals("Success", body.getMessage());
        verify(appService).addNew((OrderRequest) any());
    }

    /**
     * Method under test: {@link OrderController#getAll(int, int)}
     */
    @Test
    void testGetAll() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        CustomerInfoRepository customerInfoRepository = mock(CustomerInfoRepository.class);
        ResponseEntity<ApiResponse> actualAll = (new OrderController(new OrderServiceImpl(orderRepository,
                customerInfoRepository, new HelpMapper(new ModelMapper()), mock(KafkaTemplateConfig.class)))).getAll(1, 3);
        assertTrue(actualAll.hasBody());
        assertTrue(actualAll.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAll.getStatusCode());
        ApiResponse body = actualAll.getBody();
        assertTrue(((Collection<Object>) body.getData()).isEmpty());
        assertEquals("Success", body.getMessage());
        assertEquals(HttpStatus.OK, body.getStatus());
        verify(orderRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link OrderController#getAll(int, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAll2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.data.domain.Page.stream()" because the return value of "com.minty.salesinventorymgt.repositories.OrderRepository.findAll(org.springframework.data.domain.Pageable)" is null
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.findAll(OrderServiceImpl.java:120)
        //       at com.minty.salesinventorymgt.controllers.OrderController.getAll(OrderController.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findAll((Pageable) any())).thenReturn(null);
        CustomerInfoRepository customerInfoRepository = mock(CustomerInfoRepository.class);
        (new OrderController(new OrderServiceImpl(orderRepository, customerInfoRepository,
                new HelpMapper(new ModelMapper()), mock(KafkaTemplateConfig.class)))).getAll(1, 3);
    }

    /**
     * Method under test: {@link OrderController#getAll(int, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAll3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero
        //       at com.minty.salesinventorymgt.controllers.OrderController.getAll(OrderController.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        CustomerInfoRepository customerInfoRepository = mock(CustomerInfoRepository.class);
        (new OrderController(new OrderServiceImpl(orderRepository, customerInfoRepository,
                new HelpMapper(new ModelMapper()), mock(KafkaTemplateConfig.class)))).getAll(-1, 3);
    }

    /**
     * Method under test: {@link OrderController#getOne(String)}
     */
    @Test
    void testGetOne() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress("42 Main St");
        customerInfo.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setEmailAddress("42 Main St");
        customerInfo.setFullName("Dr Jane Doe");
        customerInfo.setId(1L);
        customerInfo.setPhoneNumber("6625550144");
        customerInfo.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setUsername("janedoe");

        Order order = new Order();
        order.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setCustomer(customerInfo);
        order.setId(1L);
        order.setOrderItems(new HashSet<>());
        order.setOrderNumber("42");
        order.setStatus(OrderStatus.PENDING);
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        order.setTotalOrderAmount(valueOfResult);
        order.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(Optional.of(order));
        CustomerInfoRepository customerInfoRepository = mock(CustomerInfoRepository.class);
        ResponseEntity<ApiResponse> actualOne = (new OrderController(new OrderServiceImpl(orderRepository,
                customerInfoRepository, new HelpMapper(new ModelMapper()), mock(KafkaTemplateConfig.class)))).getOne("42");
        assertTrue(actualOne.hasBody());
        assertTrue(actualOne.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualOne.getStatusCode());
        ApiResponse body = actualOne.getBody();
        Object data = body.getData();
        assertTrue(data instanceof OrderResponse);
        assertEquals(HttpStatus.OK, body.getStatus());
        assertEquals("Success", body.getMessage());
        assertEquals("42", ((OrderResponse) data).getOrderNumber());
        assertTrue(((OrderResponse) data).getOrderItems().isEmpty());
        BigDecimal totalOrderAmount = ((OrderResponse) data).getTotalOrderAmount();
        assertSame(valueOfResult, totalOrderAmount);
        assertEquals("0001-01-01", ((OrderResponse) data).getCreatedDate().toLocalDate().toString());
        assertEquals("01:01", ((OrderResponse) data).getUpdatedDate().toLocalTime().toString());
        assertEquals(OrderStatus.PENDING, ((OrderResponse) data).getStatus());
        CustomerInfoResponse customer = ((OrderResponse) data).getCustomer();
        assertEquals("42 Main St", customer.getAddress());
        assertEquals("6625550144", customer.getPhoneNumber());
        assertEquals("01:01", customer.getUpdatedDate().toLocalTime().toString());
        assertEquals("Dr Jane Doe", customer.getFullName());
        assertEquals("42 Main St", customer.getEmailAddress());
        assertEquals("janedoe", customer.getUsername());
        assertEquals("01:01", customer.getCreatedDate().toLocalTime().toString());
        assertEquals("42", totalOrderAmount.toString());
        verify(orderRepository).findByOrderNumber((String) any());
    }

    /**
     * Method under test: {@link OrderController#getOne(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOne2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.minty.salesinventorymgt.exceptions.NotFoundException: Order does not exist
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.lambda$findOneEntity$0(OrderServiceImpl.java:114)
        //       at java.util.Optional.orElseThrow(Optional.java:403)
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.findOneEntity(OrderServiceImpl.java:114)
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.findOne(OrderServiceImpl.java:102)
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.findOne(OrderServiceImpl.java:30)
        //       at com.minty.salesinventorymgt.controllers.OrderController.getOne(OrderController.java:52)
        //   See https://diff.blue/R013 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(Optional.empty());
        CustomerInfoRepository customerInfoRepository = mock(CustomerInfoRepository.class);
        (new OrderController(new OrderServiceImpl(orderRepository, customerInfoRepository,
                new HelpMapper(new ModelMapper()), mock(KafkaTemplateConfig.class)))).getOne("42");
    }

    /**
     * Method under test: {@link OrderController#updateOne(String, OrderRequest)}
     */
    @Test
    void testUpdateOne() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress("42 Main St");
        customerInfo.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setEmailAddress("42 Main St");
        customerInfo.setFullName("Dr Jane Doe");
        customerInfo.setId(1L);
        customerInfo.setPhoneNumber("6625550144");
        customerInfo.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setUsername("janedoe");

        Order order = new Order();
        order.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setCustomer(customerInfo);
        order.setId(1L);
        order.setOrderItems(new HashSet<>());
        order.setOrderNumber("42");
        order.setStatus(OrderStatus.PENDING);
        order.setTotalOrderAmount(BigDecimal.valueOf(42L));
        order.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(Optional.of(order));
        CustomerInfoRepository customerInfoRepository = mock(CustomerInfoRepository.class);
        OrderController orderController = new OrderController(new OrderServiceImpl(orderRepository,
                customerInfoRepository, new HelpMapper(new ModelMapper()), mock(KafkaTemplateConfig.class)));
        ResponseEntity<ApiResponse> actualUpdateOneResult = orderController.updateOne("Code", new OrderRequest());
        assertTrue(actualUpdateOneResult.hasBody());
        assertTrue(actualUpdateOneResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUpdateOneResult.getStatusCode());
        ApiResponse body = actualUpdateOneResult.getBody();
        Object data = body.getData();
        assertTrue(data instanceof OrderResponse);
        assertEquals(HttpStatus.OK, body.getStatus());
        assertEquals("Success", body.getMessage());
        assertEquals("42", ((OrderResponse) data).getOrderNumber());
        assertTrue(((OrderResponse) data).getOrderItems().isEmpty());
        assertNull(((OrderResponse) data).getTotalOrderAmount());
        assertEquals("0001-01-01", ((OrderResponse) data).getCreatedDate().toLocalDate().toString());
        assertEquals("01:01", ((OrderResponse) data).getUpdatedDate().toLocalTime().toString());
        assertEquals(OrderStatus.PENDING, ((OrderResponse) data).getStatus());
        CustomerInfoResponse customer = ((OrderResponse) data).getCustomer();
        assertEquals("Dr Jane Doe", customer.getFullName());
        assertEquals("42 Main St", customer.getEmailAddress());
        assertEquals("42 Main St", customer.getAddress());
        assertEquals("01:01", customer.getUpdatedDate().toLocalTime().toString());
        assertEquals("janedoe", customer.getUsername());
        assertEquals("6625550144", customer.getPhoneNumber());
        assertEquals("0001-01-01", customer.getCreatedDate().toLocalDate().toString());
        verify(orderRepository).findByOrderNumber((String) any());
    }

    /**
     * Method under test: {@link OrderController#updateOne(String, OrderRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateOne2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.minty.salesinventorymgt.exceptions.NotFoundException: Order does not exist
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.lambda$findOneEntity$0(OrderServiceImpl.java:114)
        //       at java.util.Optional.orElseThrow(Optional.java:403)
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.findOneEntity(OrderServiceImpl.java:114)
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.updateOne(OrderServiceImpl.java:126)
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.updateOne(OrderServiceImpl.java:30)
        //       at com.minty.salesinventorymgt.controllers.OrderController.updateOne(OrderController.java:63)
        //   See https://diff.blue/R013 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(Optional.empty());
        CustomerInfoRepository customerInfoRepository = mock(CustomerInfoRepository.class);
        OrderController orderController = new OrderController(new OrderServiceImpl(orderRepository,
                customerInfoRepository, new HelpMapper(new ModelMapper()), mock(KafkaTemplateConfig.class)));
        orderController.updateOne("Code", new OrderRequest());
    }
}

