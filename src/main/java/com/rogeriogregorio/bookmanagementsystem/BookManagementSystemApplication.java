package com.rogeriogregorio.bookmanagementsystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Book Management System", version = "1", description = "API desenvolvida para gerenciamento de livros"))
public class BookManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManagementSystemApplication.class, args);
	}

}
