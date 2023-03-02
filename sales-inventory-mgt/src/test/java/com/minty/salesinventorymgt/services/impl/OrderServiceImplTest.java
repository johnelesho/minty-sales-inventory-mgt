package com.minty.salesinventorymgt.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.minty.lib.dtos.request.OrderRequest;
import com.minty.lib.dtos.response.OrderResponse;
import com.minty.lib.enums.OrderStatus;
import com.minty.lib.mappers.HelpMapper;
import com.minty.lib.models.CustomerInfo;
import com.minty.lib.models.Order;
import com.minty.salesinventorymgt.config.KafkaTemplateConfig;
import com.minty.salesinventorymgt.exceptions.BadRequestException;
import com.minty.salesinventorymgt.exceptions.NotFoundException;
import com.minty.salesinventorymgt.repositories.CustomerInfoRepository;
import com.minty.salesinventorymgt.repositories.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {
    @MockBean
    private CustomerInfoRepository customerInfoRepository;

    @MockBean
    private HelpMapper helpMapper;

    @MockBean
    private KafkaTemplateConfig kafkaTemplateConfig;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    /**
     * Method under test: {@link OrderServiceImpl#placeOrder(OrderRequest)}
     */
    @Test
    void testPlaceOrder() {
        assertThrows(BadRequestException.class, () -> orderServiceImpl.placeOrder(new OrderRequest()));
        assertThrows(BadRequestException.class, () -> orderServiceImpl.placeOrder(null));
    }

    /**
     * Method under test: {@link OrderServiceImpl#placeOrder(OrderRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPlaceOrder2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.minty.lib.dtos.request.OrderRequest.getCustomer()" because "request" is null
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.placeOrder(OrderServiceImpl.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        orderServiceImpl.placeOrder(null);
    }

    /**
     * Method under test: {@link OrderServiceImpl#isExist(String)}
     */
    @Test
    void testIsExist() {
        when(orderRepository.existsByOrderNumberIgnoreCase((String) any())).thenReturn(true);
        assertTrue(orderServiceImpl.isExist("Key"));
        verify(orderRepository).existsByOrderNumberIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#isExist(String)}
     */
    @Test
    void testIsExist2() {
        when(orderRepository.existsByOrderNumberIgnoreCase((String) any())).thenReturn(false);
        assertFalse(orderServiceImpl.isExist("Key"));
        verify(orderRepository).existsByOrderNumberIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#isExist(String)}
     */
    @Test
    void testIsExist3() {
        when(orderRepository.existsByOrderNumberIgnoreCase((String) any()))
                .thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> orderServiceImpl.isExist("Key"));
        verify(orderRepository).existsByOrderNumberIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#findOne(String)}
     */
    @Test
    void testFindOne() {
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
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(ofResult);
        OrderResponse orderResponse = new OrderResponse();
        when(helpMapper.convertToOrderResonse((Order) any())).thenReturn(orderResponse);
        assertSame(orderResponse, orderServiceImpl.findOne("42"));
        verify(orderRepository).findByOrderNumber((String) any());
        verify(helpMapper).convertToOrderResonse((Order) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#findOne(String)}
     */
    @Test
    void testFindOne2() {
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
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(ofResult);
        when(helpMapper.convertToOrderResonse((Order) any())).thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> orderServiceImpl.findOne("42"));
        verify(orderRepository).findByOrderNumber((String) any());
        verify(helpMapper).convertToOrderResonse((Order) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#findOne(String)}
     */
    @Test
    void testFindOne3() {
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(Optional.empty());
        when(helpMapper.convertToOrderResonse((Order) any())).thenReturn(new OrderResponse());
        assertThrows(NotFoundException.class, () -> orderServiceImpl.findOne("42"));
        verify(orderRepository).findByOrderNumber((String) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#addNew(OrderRequest)}
     */
    @Test
    void testAddNew() {
        assertThrows(BadRequestException.class, () -> orderServiceImpl.addNew(new OrderRequest()));
        assertThrows(BadRequestException.class, () -> orderServiceImpl.addNew(null));
    }

    /**
     * Method under test: {@link OrderServiceImpl#addNew(OrderRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddNew2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.minty.lib.dtos.request.OrderRequest.getCustomer()" because "request" is null
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.placeOrder(OrderServiceImpl.java:50)
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.addNew(OrderServiceImpl.java:109)
        //   See https://diff.blue/R013 to resolve this issue.

        orderServiceImpl.addNew(null);
    }

    /**
     * Method under test: {@link OrderServiceImpl#findOneEntity(String)}
     */
    @Test
    void testFindOneEntity() {
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
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(ofResult);
        Order actualFindOneEntityResult = orderServiceImpl.findOneEntity("42");
        assertSame(order, actualFindOneEntityResult);
        assertEquals("42", actualFindOneEntityResult.getTotalOrderAmount().toString());
        verify(orderRepository).findByOrderNumber((String) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#findOneEntity(String)}
     */
    @Test
    void testFindOneEntity2() {
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> orderServiceImpl.findOneEntity("42"));
        verify(orderRepository).findByOrderNumber((String) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#findOneEntity(String)}
     */
    @Test
    void testFindOneEntity3() {
        when(orderRepository.findByOrderNumber((String) any())).thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> orderServiceImpl.findOneEntity("42"));
        verify(orderRepository).findByOrderNumber((String) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#findAll(PageRequest)}
     */
    @Test
    void testFindAll() {
        when(orderRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(orderServiceImpl.findAll(null).isEmpty());
        verify(orderRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#findAll(PageRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAll2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.minty.salesinventorymgt.exceptions.BadRequestException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$Itr.forEachRemaining(ArrayList.java:1003)
        //       at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1845)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
        //       at java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
        //       at java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
        //       at java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
        //       at java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.findAll(OrderServiceImpl.java:120)
        //   See https://diff.blue/R013 to resolve this issue.

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

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order);
        PageImpl<Order> pageImpl = new PageImpl<>(orderList);
        when(orderRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        when(helpMapper.convertToOrderResonse((Order) any())).thenThrow(new BadRequestException("An error occurred"));
        orderServiceImpl.findAll(null);
    }

    /**
     * Method under test: {@link OrderServiceImpl#findAll(PageRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAll3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.data.domain.Page.stream()" because the return value of "com.minty.salesinventorymgt.repositories.OrderRepository.findAll(org.springframework.data.domain.Pageable)" is null
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.findAll(OrderServiceImpl.java:120)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderRepository.findAll((Pageable) any())).thenReturn(null);
        when(helpMapper.convertToOrderResonse((Order) any())).thenThrow(new BadRequestException("An error occurred"));
        orderServiceImpl.findAll(null);
    }

    /**
     * Method under test: {@link OrderServiceImpl#findAll(PageRequest)}
     */
    @Test
    void testFindAll4() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(orderServiceImpl.findAll(null).isEmpty());
        verify(orderRepository).findAll();
    }

    /**
     * Method under test: {@link OrderServiceImpl#findAll(PageRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAll5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.minty.salesinventorymgt.exceptions.BadRequestException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
        //       at java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
        //       at java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
        //       at java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
        //       at java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.findAll(OrderServiceImpl.java:121)
        //   See https://diff.blue/R013 to resolve this issue.

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

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order);
        when(orderRepository.findAll()).thenReturn(orderList);
        when(helpMapper.convertToOrderResonse((Order) any())).thenThrow(new BadRequestException("An error occurred"));
        orderServiceImpl.findAll(null);
    }

    /**
     * Method under test: {@link OrderServiceImpl#updateOne(String, OrderRequest)}
     */
    @Test
    void testUpdateOne() {
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
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(ofResult);
        OrderResponse orderResponse = new OrderResponse();
        when(helpMapper.convertToOrderResonse((Order) any())).thenReturn(orderResponse);
        assertSame(orderResponse, orderServiceImpl.updateOne("Unique Key", new OrderRequest()));
        verify(orderRepository).findByOrderNumber((String) any());
        verify(helpMapper).convertToOrderResonse((Order) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#updateOne(String, OrderRequest)}
     */
    @Test
    void testUpdateOne2() {
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
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(ofResult);
        when(helpMapper.convertToOrderResonse((Order) any())).thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> orderServiceImpl.updateOne("Unique Key", new OrderRequest()));
        verify(orderRepository).findByOrderNumber((String) any());
        verify(helpMapper).convertToOrderResonse((Order) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#updateOne(String, OrderRequest)}
     */
    @Test
    void testUpdateOne3() {
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(Optional.empty());
        when(helpMapper.convertToOrderResonse((Order) any())).thenReturn(new OrderResponse());
        assertThrows(NotFoundException.class, () -> orderServiceImpl.updateOne("Unique Key", new OrderRequest()));
        verify(orderRepository).findByOrderNumber((String) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#updateOne(String, OrderRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateOne4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Source must not be null
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.updateOne(OrderServiceImpl.java:127)
        //   See https://diff.blue/R013 to resolve this issue.

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
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(ofResult);
        when(helpMapper.convertToOrderResonse((Order) any())).thenReturn(new OrderResponse());
        orderServiceImpl.updateOne("Unique Key", null);
    }

    /**
     * Method under test: {@link OrderServiceImpl#updateOne(String, OrderRequest)}
     */
    @Test
    void testUpdateOne5() {
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
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(ofResult);
        OrderResponse orderResponse = new OrderResponse();
        when(helpMapper.convertToOrderResonse((Order) any())).thenReturn(orderResponse);
        OrderRequest orderRequest = mock(OrderRequest.class);
        when(orderRequest.getStatus()).thenReturn(OrderStatus.PENDING);
        when(orderRequest.getTotalOrderAmount()).thenReturn(BigDecimal.valueOf(42L));
        assertSame(orderResponse, orderServiceImpl.updateOne("Unique Key", orderRequest));
        verify(orderRepository).findByOrderNumber((String) any());
        verify(helpMapper).convertToOrderResonse((Order) any());
        verify(orderRequest).getStatus();
        verify(orderRequest).getTotalOrderAmount();
    }

    /**
     * Method under test: {@link OrderServiceImpl#updateOne(String, OrderRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateOne6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "com.minty.salesinventorymgt.repositories.OrderRepository.findByOrderNumber(String)" is null
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.findOneEntity(OrderServiceImpl.java:114)
        //       at com.minty.salesinventorymgt.services.impl.OrderServiceImpl.updateOne(OrderServiceImpl.java:132)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderRepository.findByOrderNumber((String) any())).thenReturn(null);
        when(helpMapper.convertToOrderResonse((Order) any())).thenReturn(new OrderResponse());
        orderServiceImpl.updateOne("Unique Key", new OrderRequest());
    }

    /**
     * Method under test: {@link OrderServiceImpl#updateOne(String, OrderRequest)}
     */
    @Test
    void testUpdateOne7() {
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
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findByOrderNumber((String) any())).thenReturn(ofResult);
        when(helpMapper.convertToOrderResonse((Order) any())).thenReturn(new OrderResponse());
        assertThrows(BadRequestException.class, () -> orderServiceImpl.updateOne("Unique Key", null));
    }
}

