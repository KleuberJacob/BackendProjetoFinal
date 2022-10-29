package br.com.xmarket.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI xMarketApi() {
	return new OpenAPI()
		.components(new Components())
		.info(new Info()
		.title("Simple Spring Boot REST API XMarket")
		.description("Ecommerce utilizando Spring Boot REST API"));				
	}
}
