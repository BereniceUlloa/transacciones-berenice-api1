package com.soaint.berenice.api.pdtapi1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PdtBereniceApi1Application {

	public static void main(String[] args) {
		SpringApplication.run(PdtBereniceApi1Application.class, args);
	}

}
