package com.hushunjian.gradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class GradleApplication {
	public static void main(String[] args) {
		SpringApplication.run(GradleApplication.class, args);
	}
}
