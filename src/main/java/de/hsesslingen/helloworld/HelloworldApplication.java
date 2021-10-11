package de.hsesslingen.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class HelloworldApplication {

	private Logger logger = LoggerFactory.getLogger(HelloworldApplication.class);

	@Value("${my.property:unset}")
	String internalProperty;
	List<String> visitors = new ArrayList<String>();

	@GetMapping("/visitors") // "/" is default
	public List<String> getVisitors() {
		logger.info("Visitor List requested");
		return visitors;
	}

	@PostMapping("/visit/{paramProperty}")
	public List<String> setVisitor(@PathVariable String paramProperty) {
		logger.info("New visitor added: {}", paramProperty);
		this.visitors.add(paramProperty);
		return this.visitors;
	}

	@DeleteMapping("/visit/{paramProperty}")
	public List<String> deleteVisitor(@PathVariable String paramProperty) {
		logger.info("Visitor deleted: {}", paramProperty);
		this.visitors.remove(paramProperty);
		return this.visitors;
	}

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
