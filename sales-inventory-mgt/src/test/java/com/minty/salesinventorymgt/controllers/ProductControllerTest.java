//package com.minty.salesinventorymgt.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.minty.salesinventorymgt.dtos.request.ProductRequest;
//import com.minty.salesinventorymgt.models.Product;
//import com.minty.salesinventorymgt.repositories.ProductRepository;
//import com.minty.salesinventorymgt.services.impl.ProductServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//class ProductControllerTest {
//    @MockBean
//    ProductServiceImpl productService;
//
//    @Autowired
//    ProductRepository productRepository
//
//    @Autowired
//    MockMvc mockMvc;
//
//
//    @Autowired
//    ObjectMapper objectMapper;
//    @Test
//    void createNew() throws Exception {
//        ProductRequest productRequest = ProductRequest.builder()
//                .name("ProductTest One")
//                .price(BigDecimal.valueOf(450))
//                .description("A Simple Product for Test")
//                .generalDiscount(BigDecimal.TEN)
//                .quantityInStock(20l)
//                .build();
//
//        mockMvc.perform(post("api/products")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(productRequest)))
//                .andExpect(status().isOk());
//
//        Product product = productRepository.findByNameIgnoreCase(productRequest.getName());
//        assertThat(product.getAllowableMinimumQuantityInStock()).isEqualTo(0);
//    }
//
//    @Test
//    void getAll() {
//        Product p1 = Product.builder()
//                .build()
//        List<Employee> employees = Arrays.asList(employee);
//
//        Mockito.when(productService.findAll()).thenReturn(employees);
//
//        mockMvc.perform(get("/employee"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", Matchers.hasSize(1)))
//                .andExpect(jsonPath("$[0].firstName", Matchers.is("Lokesh")));
//    }
//
//    @Test
//    void getOne() {
//    }
//
//    @Test
//    void updateOne() {
//    }
//}