package com.nexushr.nexushr_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class NexushrServerApplication {

	@RequestMapping("/")
	String home() {
		return "Hello World";
	}

	public static void main(String[] args) {
		SpringApplication.run(NexushrServerApplication.class, args);
	}

}
