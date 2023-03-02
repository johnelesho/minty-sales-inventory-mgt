package com.minty.salesinventorymgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan("com.minty.lib.models")
@ComponentScan(basePackages = {"com.minty.lib.mappers", "com.minty.salesinventorymgt"})
public class SalesInventoryMgtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesInventoryMgtApplication.class, args);
    }


}
