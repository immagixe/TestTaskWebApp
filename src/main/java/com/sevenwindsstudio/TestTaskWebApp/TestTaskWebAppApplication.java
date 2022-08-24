package com.sevenwindsstudio.TestTaskWebApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestTaskWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestTaskWebAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper () {
		return new ModelMapper();
	}
}
