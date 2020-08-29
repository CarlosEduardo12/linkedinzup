package com.carlos.linkedinzup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.carlos.linkedinzup.domain.model")
public class LinkedinzupApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkedinzupApplication.class, args);
    }

}
