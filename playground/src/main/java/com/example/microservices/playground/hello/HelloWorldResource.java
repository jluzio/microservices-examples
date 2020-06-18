package com.example.microservices.playground.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {
	
	@GetMapping("/api/hello")
	public String hello() {
		return "Hello World!";
	}

}
