package com.bupa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GestionTextilApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionTextilApplication.class, args);
	}

}

