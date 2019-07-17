package com.kakxix.base.common.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BaseCommonEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseCommonEurekaApplication.class, args);
	}

}
