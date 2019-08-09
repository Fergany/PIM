package com.commercetools.Importer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ImporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImporterApplication.class, args);
	}

}
