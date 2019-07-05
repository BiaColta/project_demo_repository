package com.example.demo;

//import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	private NumberArrangerService numberArrangerService;

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			List<Integer> numbers= Arrays.asList(85, 10, 56, 7, 2, 101);

			List<Integer> filter = numberArrangerService.filter(numbers, 10);

			System.out.println(filter);

		};

	}

}
