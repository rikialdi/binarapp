package com.binar.binar;

import com.binar.binar.controller.fileupload.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EntityScan("com.binar.binar.entity")
@EnableConfigurationProperties({
		FileStorageProperties.class
})
@SpringBootApplication
@OpenAPIDefinition
//@EnableSwagger2
@EnableScheduling
public class BinarApplication {
	public static void main(String[] args) {
		SpringApplication.run(BinarApplication.class, args);
	}
}
