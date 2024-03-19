package com.techtest.scrumretroapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableJpaRepositories
@EntityScan
public class ScrumRetroApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScrumRetroApiApplication.class, args);
    }
}
