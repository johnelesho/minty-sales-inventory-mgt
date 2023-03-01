package com.minty.salesinventorymgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaAuditing
@RestController
public class SalesInventoryMgtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesInventoryMgtApplication.class, args);
    }


}
