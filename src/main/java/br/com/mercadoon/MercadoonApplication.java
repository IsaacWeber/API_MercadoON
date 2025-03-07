package br.com.mercadoon;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Mercado ON - OpenApi", version = "1.0", description = "API E-Commerce"))
@SpringBootApplication
public class MercadoonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadoonApplication.class, args);
	}

}
