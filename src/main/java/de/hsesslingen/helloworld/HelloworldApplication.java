package de.hsesslingen.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class HelloworldApplication {

	private Logger logger = LoggerFactory.getLogger(HelloworldApplication.class);

	@Value("${my.property:unset}")
	String internalProperty;

	@GetMapping("/hello") // "/" is default
	public String sayHello() {
		logger.info("Current internal Property {}", internalProperty);
		return "Hallo Esslingen und Welt " + this.internalProperty;
	}

	@PostMapping("/setProperty/{paramProperty}")
	public String setProperty(@PathVariable String paramProperty) {
		logger.info("Old property: {} New property: {}", internalProperty, paramProperty);
		this.internalProperty = paramProperty;
		//System.out.println("In set Property. New internal property: " + internalProperty);
		return this.internalProperty;
	}

	// @PutMapping
	// @DeleteMapping
	// @RequestMapping(method = POST, ...)

	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}

}
