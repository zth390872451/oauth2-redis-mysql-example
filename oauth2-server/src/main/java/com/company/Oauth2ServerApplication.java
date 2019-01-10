package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication

public class Oauth2ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }
}
