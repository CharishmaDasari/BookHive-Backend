package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // To make application as Eureka server
public class EurekaServerRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerRegistryApplication.class, args);
	}

}
