package org.application.core.producttestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProductTestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductTestApiApplication.class, args);
    }

}
