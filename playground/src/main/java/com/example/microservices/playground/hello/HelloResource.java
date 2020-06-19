package com.example.microservices.playground.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/api/demo/hello")
	public String hello() {
		return "Hello World!";
	}

	@GetMapping("/api/demo/hello-i18n")
	public String helloI18n() {
		return messageSource.getMessage("hello", null, LocaleContextHolder.getLocale());
	}

	@GetMapping("/api/demo/hello-i18n-v1")
	public String helloI18n(@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) Locale locale) {
		return messageSource.getMessage("hello", null, locale);
	}

}
