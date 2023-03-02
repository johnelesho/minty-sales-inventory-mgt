package com.minty.salesinventorymgt;

import com.minty.lib.dtos.request.OrderRequest;
import com.minty.lib.dtos.request.ProductRequest;
import com.minty.lib.dtos.response.OrderResponse;
import com.minty.lib.dtos.response.ProductResponse;
import com.minty.lib.models.Order;
import com.minty.lib.models.Product;
import com.minty.salesinventorymgt.controllers.OrderController;
import com.minty.salesinventorymgt.controllers.ProductController;
import com.minty.salesinventorymgt.services.AppService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SalesInventoryMgtApplicationTests {


    @Autowired
    ProductController productController;

    @Autowired
    OrderController orderController;

    @Autowired
    AppService<Order, OrderRequest, OrderResponse> orderService;

    @Autowired
    AppService<Product, ProductRequest, ProductResponse> productService;

    @Test
    public void contextLoads() {
        assertThat(productController).isNotNull();
        assertThat(orderController).isNotNull();
        assertThat(productService).isNotNull();
        assertThat(orderService).isNotNull();
    }

}
