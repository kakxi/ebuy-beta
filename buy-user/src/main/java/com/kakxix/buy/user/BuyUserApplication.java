package com.kakxix.buy.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BuyUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyUserApplication.class, args);
	}

}
