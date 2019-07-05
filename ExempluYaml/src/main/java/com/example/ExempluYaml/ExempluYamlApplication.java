package com.example.ExempluYaml;

import com.example.ExempluYaml.config.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExempluYamlApplication implements CommandLineRunner {


	@Autowired
	private ServerProperties serverProperties;

	@Override
	public void run(String... args) {
		System.out.println(serverProperties);
	}


	public static void main(String[] args) {
		SpringApplication.run(ExempluYamlApplication.class, args);
	}

}
