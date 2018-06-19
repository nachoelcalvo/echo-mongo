package com.personal.mongo.echomongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EchoMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EchoMongoApplication.class, args);
	}
}
