package com.java.SpringbootRest.helloWOrld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";

	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World Bean");

	}
	
	@GetMapping(path = "/hello-world-bean/path-param/{name}")
	public HelloWorldBean helloWorldBeanPathParam(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World Bean, %s", name));
	}
	
	@GetMapping(path = "/helloWorldInternationlised")
	public String HelloWorldInternationlised() {
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale() );
	}
	
	/*
	 * @GetMapping(path = "/helloWorldInternationlised") public String
	 * HelloWorldInternationlised() { return "good.morning"; }
	 */
	
}
