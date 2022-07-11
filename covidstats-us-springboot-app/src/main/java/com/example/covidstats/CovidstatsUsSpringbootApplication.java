package com.example.covidstats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CovidstatsUsSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidstatsUsSpringbootApplication.class, args);
	}

}
