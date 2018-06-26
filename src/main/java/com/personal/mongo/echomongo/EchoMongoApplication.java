package com.personal.mongo.echomongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = {EmbeddedMongoAutoConfiguration.class})
@EnableCaching
public class EchoMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EchoMongoApplication.class, args);
	}
}
