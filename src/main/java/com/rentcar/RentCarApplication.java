package com.rentcar;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@SpringBootApplication
public class RentCarApplication {
    @Bean
    public RestTemplate getresttemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) throws IOException {
        PropertyConfigurator.configure("log4j.properties");
        SpringApplication.run(RentCarApplication.class, args);


    }

	/* Método de prueba

	@GetMapping
	public String holaMundo(){
		return "Hola Mundo";
	}*/
}
