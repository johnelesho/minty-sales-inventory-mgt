package com.minty.salesinventorymgt.services.impl;

import com.minty.lib.dtos.request.ProductRequest;
import com.minty.lib.dtos.response.ProductResponse;
import com.minty.lib.enums.ProductStatus;
import com.minty.lib.mappers.HelpMapper;
import com.minty.lib.models.Product;
import com.minty.salesinventorymgt.exceptions.BadRequestException;
import com.minty.salesinventorymgt.exceptions.NotFoundException;
import com.minty.salesinventorymgt.repositories.ProductRepository;
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

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private HelpMapper helpMapper;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    /**
     * Method under test: {@link ProductServiceImpl#isExist(String)}
     */
    @Test
    void testIsExist() {
        when(productRepository.existsByCodeIgnoreCase((String) any())).thenReturn(true);
        assertTrue(productServiceImpl.isExist("Unique Key"));
        verify(productRepository).existsByCodeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#isExist(String)}
     */
    @Test
    void testIsExist2() {
        when(productRepository.existsByCodeIgnoreCase((String) any())).thenReturn(false);
        assertFalse(productServiceImpl.isExist("Unique Key"));
        verify(productRepository).existsByCodeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#isExist(String)}
     */
    @Test
    void testIsExist3() {
        when(productRepository.existsByCodeIgnoreCase((String) any()))
                .thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> productServiceImpl.isExist("Unique Key"));
        verify(productRepository).existsByCodeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#isExistByName(String)}
     */
    @Test
    void testIsExistByName() {
        when(productRepository.existsByNameIgnoreCase((String) any())).thenReturn(true);
        assertTrue(productServiceImpl.isExistByName("Name"));
        verify(productRepository).existsByNameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#isExistByName(String)}
     */
    @Test
    void testIsExistByName2() {
        when(productRepository.existsByNameIgnoreCase((String) any())).thenReturn(false);
        assertFalse(productServiceImpl.isExistByName("Name"));
        verify(productRepository).existsByNameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#isExistByName(String)}
     */
    @Test
    void testIsExistByName3() {
        when(productRepository.existsByNameIgnoreCase((String) any()))
                .thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> productServiceImpl.isExistByName("Name"));
        verify(productRepository).existsByNameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#findOneEntity(String)}
     */
    @Test
    void testFindOneEntity() {
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
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findByCodeIgnoreCase((String) any())).thenReturn(ofResult);
        Product actualFindOneEntityResult = productServiceImpl.findOneEntity("Unique Key");
        assertSame(product, actualFindOneEntityResult);
        assertEquals("42", actualFindOneEntityResult.getGeneralDiscount().toString());
        assertEquals("42", actualFindOneEntityResult.getPrice().toString());
        verify(productRepository).findByCodeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#findOneEntity(String)}
     */
    @Test
    void testFindOneEntity2() {
        when(productRepository.findByCodeIgnoreCase((String) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> productServiceImpl.findOneEntity("Unique Key"));
        verify(productRepository).findByCodeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#findOneEntity(String)}
     */
    @Test
    void testFindOneEntity3() {
        when(productRepository.findByCodeIgnoreCase((String) any()))
                .thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> productServiceImpl.findOneEntity("Unique Key"));
        verify(productRepository).findByCodeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#findOne(String)}
     */
    @Test
    void testFindOne() {
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
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findByCodeIgnoreCase((String) any())).thenReturn(ofResult);
        ProductResponse productResponse = new ProductResponse();
        when(helpMapper.convertToProductResonse((Product) any())).thenReturn(productResponse);
        assertSame(productResponse, productServiceImpl.findOne("Unique Key"));
        verify(productRepository).findByCodeIgnoreCase((String) any());
        verify(helpMapper).convertToProductResonse((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#findOne(String)}
     */
    @Test
    void testFindOne2() {
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
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findByCodeIgnoreCase((String) any())).thenReturn(ofResult);
        when(helpMapper.convertToProductResonse((Product) any())).thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> productServiceImpl.findOne("Unique Key"));
        verify(productRepository).findByCodeIgnoreCase((String) any());
        verify(helpMapper).convertToProductResonse((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#findOne(String)}
     */
    @Test
    void testFindOne3() {
        when(productRepository.findByCodeIgnoreCase((String) any())).thenReturn(Optional.empty());
        when(helpMapper.convertToProductResonse((Product) any())).thenReturn(new ProductResponse());
        assertThrows(NotFoundException.class, () -> productServiceImpl.findOne("Unique Key"));
        verify(productRepository).findByCodeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#addNew(ProductRequest)}
     */
    @Test
    void testAddNew() {
        when(productRepository.existsByNameIgnoreCase((String) any())).thenReturn(true);
        assertThrows(BadRequestException.class, () -> productServiceImpl.addNew(new ProductRequest()));
        verify(productRepository).existsByNameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#addNew(ProductRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddNew2() throws ParseException {

        when(productRepository.existsByNameIgnoreCase((String) any())).thenReturn(false);

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
        when(helpMapper.convertToProductEntity((ProductRequest) any())).thenReturn(product);
        productServiceImpl.addNew(new ProductRequest());
    }

    /**
     * Method under test: {@link ProductServiceImpl#generateProductCode(ProductRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateProductCode() {

        productServiceImpl.generateProductCode(new ProductRequest());
    }

    /**
     * Method under test: {@link ProductServiceImpl#findAll(PageRequest)}
     */
    @Test
    void testFindAll() {
        when(productRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(productServiceImpl.findAll(null).isEmpty());
        verify(productRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#findAll(PageRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAll2() {

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

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        PageImpl<Product> pageImpl = new PageImpl<>(productList);
        when(productRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        when(helpMapper.convertToProductResonse((Product) any())).thenThrow(new BadRequestException("An error occurred"));
        productServiceImpl.findAll(null);
    }

    /**
     * Method under test: {@link ProductServiceImpl#findAll(PageRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAll3() {

        when(productRepository.findAll((Pageable) any())).thenReturn(null);
        when(helpMapper.convertToProductResonse((Product) any())).thenThrow(new BadRequestException("An error occurred"));
        productServiceImpl.findAll(null);
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateOne(String, ProductRequest)}
     */
    @Test
    void testUpdateOne() {
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
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findByCodeIgnoreCase((String) any())).thenReturn(ofResult);
        ProductResponse productResponse = new ProductResponse();
        when(helpMapper.convertToProductResonse((Product) any())).thenReturn(productResponse);
        assertSame(productResponse, productServiceImpl.updateOne("Unique Key", new ProductRequest()));
        verify(productRepository).findByCodeIgnoreCase((String) any());
        verify(helpMapper).convertToProductResonse((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateOne(String, ProductRequest)}
     */
    @Test
    void testUpdateOne2() {
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
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findByCodeIgnoreCase((String) any())).thenReturn(ofResult);
        when(helpMapper.convertToProductResonse((Product) any())).thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> productServiceImpl.updateOne("Unique Key", new ProductRequest()));
        verify(productRepository).findByCodeIgnoreCase((String) any());
        verify(helpMapper).convertToProductResonse((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateOne(String, ProductRequest)}
     */
    @Test
    void testUpdateOne3() {
        when(productRepository.findByCodeIgnoreCase((String) any())).thenReturn(Optional.empty());
        when(helpMapper.convertToProductResonse((Product) any())).thenReturn(new ProductResponse());
        assertThrows(NotFoundException.class, () -> productServiceImpl.updateOne("Unique Key", new ProductRequest()));
        verify(productRepository).findByCodeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateOne(String, ProductRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateOne4() {
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
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findByCodeIgnoreCase((String) any())).thenReturn(ofResult);
        when(helpMapper.convertToProductResonse((Product) any())).thenReturn(new ProductResponse());
        productServiceImpl.updateOne("Unique Key", null);
    }
}

