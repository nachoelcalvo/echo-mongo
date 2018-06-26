package com.personal.mongo.echomongo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Disabled
@ActiveProfiles("embedded_db")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EchoMongoApplicationTests {

	@Test
	public void contextLoads() {
	}
}
