package com.finalProject.CurrencyConversionProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CurrencyConversionProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionProjectApplication.class, args);
	}

}
