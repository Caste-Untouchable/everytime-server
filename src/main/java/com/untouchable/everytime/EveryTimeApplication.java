package com.untouchable.everytime;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class EveryTimeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EveryTimeApplication.class, args);
	}

}