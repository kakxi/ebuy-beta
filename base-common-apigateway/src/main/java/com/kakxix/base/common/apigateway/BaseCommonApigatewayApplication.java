package com.kakxix.base.common.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BaseCommonApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseCommonApigatewayApplication.class, args);
	}

}
