package com.demo.openapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenApisApplication.class, args);
	}

}
