package com.untouchable.everytime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class EveryTimeApplicationTests {

	@Value("${jwt.secret}")
	String key ;

	@Test
	void contextLoads() {
		System.out.println(key);
	}

}
