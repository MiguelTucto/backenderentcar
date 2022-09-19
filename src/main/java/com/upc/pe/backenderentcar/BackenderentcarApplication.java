package com.upc.pe.backenderentcar;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class BackenderentcarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackenderentcarApplication.class, args);
    }

}
