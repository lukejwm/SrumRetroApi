package com.techtest.scrumretroapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ScrumRetroApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ScrumRetroApiApplication.class, args);
	}
}
