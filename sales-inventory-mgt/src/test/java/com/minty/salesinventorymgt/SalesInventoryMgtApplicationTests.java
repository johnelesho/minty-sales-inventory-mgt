package com.minty.salesinventorymgt;

import com.minty.salesinventorymgt.controllers.OrderController;
import com.minty.salesinventorymgt.controllers.ProductController;
import com.minty.salesinventorymgt.dtos.request.OrderRequest;
import com.minty.salesinventorymgt.dtos.request.ProductRequest;
import com.minty.salesinventorymgt.dtos.response.OrderResponse;
import com.minty.salesinventorymgt.dtos.response.ProductResponse;
import com.minty.salesinventorymgt.models.Order;
import com.minty.salesinventorymgt.models.Product;
import com.minty.salesinventorymgt.services.AppService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SalesInventoryMgtApplicationTests {

    //    @ClassRule
//    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
//            .withDatabaseName("integration-db")
//            .withUsername("sa")
//            .withPassword("sa");
//
//    static class Initializer
//            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//            TestPropertyValues.of(
//                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
//                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
//                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
//            ).applyTo(configurableApplicationContext.getEnvironment());
//        }
//    }
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
