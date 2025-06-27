package com.fantasmaDux.restApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fantasmaDux.restApplication")
public class RestApplication {

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("✅ PostgreSQL driver loaded!");
		} catch (ClassNotFoundException e) {
			System.err.println("❌ PostgreSQL driver NOT FOUND!");
			e.printStackTrace();
		}
		SpringApplication.run(RestApplication.class, args);
	}

}
