package com.minty.salesinventorymgt.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.minty.lib.dtos.request.ProductRequest;
import com.minty.lib.dtos.response.ApiResponse;
import com.minty.lib.dtos.response.ProductResponse;
import com.minty.lib.enums.ProductStatus;
import com.minty.lib.mappers.HelpMapper;
import com.minty.lib.models.Product;
import com.minty.salesinventorymgt.repositories.ProductRepository;
import com.minty.salesinventorymgt.services.AppService;
import com.minty.salesinventorymgt.services.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ProductControllerTest {
    /**
     * Method under test: {@link ProductController#createNew(ProductRequest)}
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
        //   com.minty.salesinventorymgt.exceptions.BadRequestException: Product already exists
        //       at com.minty.salesinventorymgt.services.impl.ProductServiceImpl.addNew(ProductServiceImpl.java:65)
        //       at com.minty.salesinventorymgt.services.impl.ProductServiceImpl.addNew(ProductServiceImpl.java:24)
        //       at com.minty.salesinventorymgt.controllers.ProductController.createNew(ProductController.java:30)
        //   See https://diff.blue/R013 to resolve this issue.

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.existsByNameIgnoreCase((String) any())).thenReturn(true);
        ProductController productController = new ProductController(
                new ProductServiceImpl(productRepository, new HelpMapper(new ModelMapper())));
        productController.createNew(new ProductRequest());
    }

    /**
     * Method under test: {@link ProductController#createNew(ProductRequest)}
     */
    @Test
    void testCreateNew2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AppService<Product, ProductRequest, ProductResponse> appService = (AppService<Product, ProductRequest, ProductResponse>) mock(
                AppService.class);
        ProductResponse productResponse = new ProductResponse();
        when(appService.addNew((ProductRequest) any())).thenReturn(productResponse);
        ProductController productController = new ProductController(appService);
        ResponseEntity<ApiResponse> actualCreateNewResult = productController.createNew(new ProductRequest());
        assertTrue(actualCreateNewResult.hasBody());
        assertTrue(actualCreateNewResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.CREATED, actualCreateNewResult.getStatusCode());
        ApiResponse body = actualCreateNewResult.getBody();
        assertSame(productResponse, body.getData());
        assertEquals(HttpStatus.CREATED, body.getStatus());
        assertEquals("Success", body.getMessage());
        verify(appService).addNew((ProductRequest) any());
    }

    /**
     * Method under test: {@link ProductController#getAll(int, int)}
     */
    @Test
    void testGetAll() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        ResponseEntity<ApiResponse> actualAll = (new ProductController(
                new ProductServiceImpl(productRepository, new HelpMapper(new ModelMapper())))).getAll(1, 3);
        assertTrue(actualAll.hasBody());
        assertTrue(actualAll.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAll.getStatusCode());
        ApiResponse body = actualAll.getBody();
        assertTrue(((Collection<Object>) body.getData()).isEmpty());
        assertEquals("Success", body.getMessage());
        assertEquals(HttpStatus.OK, body.getStatus());
        verify(productRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link ProductController#getAll(int, int)}
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
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.data.domain.Page.stream()" because the return value of "com.minty.salesinventorymgt.repositories.ProductRepository.findAll(org.springframework.data.domain.Pageable)" is null
        //       at com.minty.salesinventorymgt.services.impl.ProductServiceImpl.findAll(ProductServiceImpl.java:97)
        //       at com.minty.salesinventorymgt.controllers.ProductController.getAll(ProductController.java:43)
        //   See https://diff.blue/R013 to resolve this issue.

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findAll((Pageable) any())).thenReturn(null);
        (new ProductController(new ProductServiceImpl(productRepository, new HelpMapper(new ModelMapper())))).getAll(1,
                3);
    }

    /**
     * Method under test: {@link ProductController#getAll(int, int)}
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
        //       at com.minty.salesinventorymgt.controllers.ProductController.getAll(ProductController.java:42)
        //   See https://diff.blue/R013 to resolve this issue.

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        (new ProductController(new ProductServiceImpl(productRepository, new HelpMapper(new ModelMapper())))).getAll(-1,
                3);
    }

    /**
     * Method under test: {@link ProductController#getOne(String)}
     */
    @Test
    void testGetOne() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        Product product = new Product();
        product.setAllowableMinimumQuantityInStock(1L);
        product.setCode("Code");
        product.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setDescription("The characteristics of someone or something");
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        product.setGeneralDiscount(valueOfResult);
        product.setId(1L);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setQuantityInStock(1L);
        product.setStatus(ProductStatus.AVAILABLE);
        product.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findByCodeIgnoreCase((String) any())).thenReturn(Optional.of(product));
        ResponseEntity<ApiResponse> actualOne = (new ProductController(
                new ProductServiceImpl(productRepository, new HelpMapper(new ModelMapper())))).getOne("Code");
        assertTrue(actualOne.hasBody());
        assertTrue(actualOne.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualOne.getStatusCode());
        ApiResponse body = actualOne.getBody();
        Object data = body.getData();
        assertTrue(data instanceof ProductResponse);
        assertEquals(HttpStatus.OK, body.getStatus());
        assertEquals("Success", body.getMessage());
        BigDecimal price = ((ProductResponse) data).getPrice();
        assertEquals(valueOfResult, price);
        assertEquals("Name", ((ProductResponse) data).getName());
        assertEquals(ProductStatus.AVAILABLE, ((ProductResponse) data).getStatus());
        assertEquals("The characteristics of someone or something", ((ProductResponse) data).getDescription());
        assertEquals("01:01", ((ProductResponse) data).getUpdatedDate().toLocalTime().toString());
        BigDecimal generalDiscount = ((ProductResponse) data).getGeneralDiscount();
        assertEquals(price, generalDiscount);
        assertEquals(1L, ((ProductResponse) data).getAllowableMinimumQuantityInStock().longValue());
        assertEquals(1L, ((ProductResponse) data).getQuantityInStock().longValue());
        assertEquals("Code", ((ProductResponse) data).getCode());
        assertEquals("0001-01-01", ((ProductResponse) data).getCreatedDate().toLocalDate().toString());
        assertEquals("42", generalDiscount.toString());
        assertEquals("42", price.toString());
        verify(productRepository).findByCodeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductController#getOne(String)}
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
        //   com.minty.salesinventorymgt.exceptions.NotFoundException: Product does not exist
        //       at com.minty.salesinventorymgt.services.impl.ProductServiceImpl.lambda$findOneEntity$0(ProductServiceImpl.java:49)
        //       at java.util.Optional.orElseThrow(Optional.java:403)
        //       at com.minty.salesinventorymgt.services.impl.ProductServiceImpl.findOneEntity(ProductServiceImpl.java:49)
        //       at com.minty.salesinventorymgt.services.impl.ProductServiceImpl.findOne(ProductServiceImpl.java:55)
        //       at com.minty.salesinventorymgt.services.impl.ProductServiceImpl.findOne(ProductServiceImpl.java:24)
        //       at com.minty.salesinventorymgt.controllers.ProductController.getOne(ProductController.java:54)
        //   See https://diff.blue/R013 to resolve this issue.

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findByCodeIgnoreCase((String) any())).thenReturn(Optional.empty());
        (new ProductController(new ProductServiceImpl(productRepository, new HelpMapper(new ModelMapper()))))
                .getOne("Code");
    }

    /**
     * Method under test: {@link ProductController#updateOne(String, ProductRequest)}
     */
    @Test
    void testUpdateOne() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        Product product = new Product();
        product.setAllowableMinimumQuantityInStock(1L);
        product.setCode("Code");
        product.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setDescription("The characteristics of someone or something");
        product.setGeneralDiscount(BigDecimal.valueOf(42L));
        product.setId(1L);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setQuantityInStock(1L);
        product.setStatus(ProductStatus.AVAILABLE);
        product.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findByCodeIgnoreCase((String) any())).thenReturn(Optional.of(product));
        ProductController productController = new ProductController(
                new ProductServiceImpl(productRepository, new HelpMapper(new ModelMapper())));
        ResponseEntity<ApiResponse> actualUpdateOneResult = productController.updateOne("Code", new ProductRequest());
        assertTrue(actualUpdateOneResult.hasBody());
        assertTrue(actualUpdateOneResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUpdateOneResult.getStatusCode());
        ApiResponse body = actualUpdateOneResult.getBody();
        Object data = body.getData();
        assertTrue(data instanceof ProductResponse);
        assertEquals(HttpStatus.OK, body.getStatus());
        assertEquals("Success", body.getMessage());
        assertNull(((ProductResponse) data).getPrice());
        assertNull(((ProductResponse) data).getName());
        assertNull(((ProductResponse) data).getGeneralDiscount());
        assertNull(((ProductResponse) data).getDescription());
        assertEquals("Code", ((ProductResponse) data).getCode());
        assertEquals("01:01", ((ProductResponse) data).getCreatedDate().toLocalTime().toString());
        assertNull(((ProductResponse) data).getAllowableMinimumQuantityInStock());
        assertNull(((ProductResponse) data).getQuantityInStock());
        assertEquals("01:01", ((ProductResponse) data).getUpdatedDate().toLocalTime().toString());
        assertEquals(ProductStatus.AVAILABLE, ((ProductResponse) data).getStatus());
        verify(productRepository).findByCodeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductController#updateOne(String, ProductRequest)}
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
        //   com.minty.salesinventorymgt.exceptions.NotFoundException: Product does not exist
        //       at com.minty.salesinventorymgt.services.impl.ProductServiceImpl.lambda$findOneEntity$0(ProductServiceImpl.java:49)
        //       at java.util.Optional.orElseThrow(Optional.java:403)
        //       at com.minty.salesinventorymgt.services.impl.ProductServiceImpl.findOneEntity(ProductServiceImpl.java:49)
        //       at com.minty.salesinventorymgt.services.impl.ProductServiceImpl.updateOne(ProductServiceImpl.java:103)
        //       at com.minty.salesinventorymgt.services.impl.ProductServiceImpl.updateOne(ProductServiceImpl.java:24)
        //       at com.minty.salesinventorymgt.controllers.ProductController.updateOne(ProductController.java:65)
        //   See https://diff.blue/R013 to resolve this issue.

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findByCodeIgnoreCase((String) any())).thenReturn(Optional.empty());
        ProductController productController = new ProductController(
                new ProductServiceImpl(productRepository, new HelpMapper(new ModelMapper())));
        productController.updateOne("Code", new ProductRequest());
    }
}

