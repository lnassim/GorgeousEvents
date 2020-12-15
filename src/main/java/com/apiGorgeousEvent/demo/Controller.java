package com.apiGorgeousEvent.demo;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Controller {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private static final int EXPIRATION =100;

	@Autowired
	private UserRepository repository;

	/* @GetMapping("/greeting")
	public com.example.restservice.Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new com.example.restservice.Greeting(counter.incrementAndGet(), String.format(template, name));
	}*/


}
