package com.grocery.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.grocery.demo.repository.GroceryItemRepository;

@SpringBootApplication
public class DemoForGroceryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoForGroceryApplication.class, args);
	}

}
