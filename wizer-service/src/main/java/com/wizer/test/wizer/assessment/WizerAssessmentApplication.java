package com.wizer.test.wizer.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.wizer.*")
public class WizerAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(WizerAssessmentApplication.class, args);
	}

}
