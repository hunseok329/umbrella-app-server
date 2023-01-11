package com.example.umbrellaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class UmbrellaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmbrellaApiApplication.class, args);
    }

}
