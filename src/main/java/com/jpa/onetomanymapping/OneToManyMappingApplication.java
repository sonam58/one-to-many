package com.jpa.onetomanymapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OneToManyMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneToManyMappingApplication.class, args);
	}

}
