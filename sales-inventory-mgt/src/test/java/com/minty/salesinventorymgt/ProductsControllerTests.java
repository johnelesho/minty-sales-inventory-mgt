package com.minty.salesinventorymgt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minty.salesinventorymgt.dtos.request.ProductRequest;
import com.minty.salesinventorymgt.dtos.response.ProductResponse;
import com.minty.salesinventorymgt.models.Product;
import com.minty.salesinventorymgt.services.AppService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.minty.salesinventorymgt.Builders.buildProductRequest;
import static com.minty.salesinventorymgt.Builders.buildProductResponse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest()
public class ProductsControllerTests {
    @MockBean
    AppService<Product, ProductRequest, ProductResponse> productService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createNewProduct() throws Exception {
        ProductRequest productRequest = buildProductRequest();
        Mockito.when(productService.addNew(productRequest)).thenReturn(buildProductResponse())
        ;
        mockMvc.perform(post("api/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isOk());

    }

    @Test
    public void testfindAll() throws Exception {
        ProductResponse p1 = buildProductResponse();

        List<ProductResponse> employees = Arrays.asList(p1);

//        Mockito.when(productService.findAll(any(Pageable.class))).thenReturn(employees);

//        mockMvc.perform(get("/api/products"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", Matchers.hasSize(1)))
//                .andExpect(jsonPath("$[0].firstName", Matchers.is("Lokesh")));
    }


}
