package com.springboot.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//below details are for Swagger url
@OpenAPIDefinition(
		info = @Info(
				title = "Spring boot Blog API Rest API's",
				description = "This is a blog app based on Java Spring boot framework",
				version = "v1.0",
				contact = @Contact(
						name = "Ayush Thakur",
						email = "AT382377@gmail.com",
						url = "https://github.com/ayushthakur24"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.Javalicense.com"
				)
		),
		externalDocs = @ExternalDocumentation(
					description = "Spring boot blog app documentation",
					url = "https://github.com/ayushthakur24/Blog-App"
				)
		)
public class Application {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
