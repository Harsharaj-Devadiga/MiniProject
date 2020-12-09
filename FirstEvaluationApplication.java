package com.MiniProject.FirstEvaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.MiniProject.FirstEvaluation.config.FileStoragePojo;

@SpringBootApplication
@EnableConfigurationProperties({ FileStoragePojo.class })
public class FirstEvaluationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstEvaluationApplication.class, args);
	}

}
