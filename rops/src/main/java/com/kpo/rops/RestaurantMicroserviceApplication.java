package com.kpo.rops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Start application class.
 */
@SpringBootApplication
public class RestaurantMicroserviceApplication {
    /**
     * Start point of auth microservice.
     */
    public static void main(String[] args) {
        SpringApplication.run(RestaurantMicroserviceApplication.class, args);
    }
}
