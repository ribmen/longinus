package com.imd.longinus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
	    org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration.class
	})
public class LonginusApplication {

	public static void main(String[] args) {
		SpringApplication.run(LonginusApplication.class, args);
	}

}
