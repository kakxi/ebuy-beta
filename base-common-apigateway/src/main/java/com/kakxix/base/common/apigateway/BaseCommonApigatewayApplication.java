package com.kakxix.base.common.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kakxix.base.common.apigateway","com.kakxix.buy.user.api"})
@EnableEurekaClient
@EnableFeignClients
public class BaseCommonApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseCommonApigatewayApplication.class, args);
	}

}
