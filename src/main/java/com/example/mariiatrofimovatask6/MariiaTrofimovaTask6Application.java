package com.example.mariiatrofimovatask6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MariiaTrofimovaTask6Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MariiaTrofimovaTask6Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MariiaTrofimovaTask6Application.class);
    }

}
