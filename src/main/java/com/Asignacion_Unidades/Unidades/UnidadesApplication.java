package com.Asignacion_Unidades.Unidades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UnidadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnidadesApplication.class, args);
	}

}
