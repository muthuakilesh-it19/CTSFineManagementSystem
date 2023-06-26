package com.violationMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
//@EnableSwagger2
public class ViolationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViolationMicroserviceApplication.class, args);

	}

}
