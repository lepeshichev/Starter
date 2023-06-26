package ru.sber.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	// http://localhost:8080/authorization?username=John
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
