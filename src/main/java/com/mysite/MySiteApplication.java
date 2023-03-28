package com.mysite;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MySiteApplication {

    @Value("${app.property.env}")
    public String environment;

    public static void main(String[] args) {
        SpringApplication.run(MySiteApplication.class, args);
    }
}
