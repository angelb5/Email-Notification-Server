package com.example.xd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class XdApplication {

    public static void main(String[] args) {
        SpringApplication.run(XdApplication.class, args);
    }

}
