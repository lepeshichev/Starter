package ru.sber.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcApplication {
	// http://localhost:8081
	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

}
