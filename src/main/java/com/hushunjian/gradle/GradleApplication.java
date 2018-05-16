package com.hushunjian.gradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
public class GradleApplication {
	public static void main(String[] args) {
		SpringApplication.run(GradleApplication.class, args);
	}
}
