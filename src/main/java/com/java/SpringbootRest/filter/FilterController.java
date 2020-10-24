package com.java.SpringbootRest.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {
	
	@GetMapping("/filtering")
	public SomeBean retriveSomeBean() {
		return new SomeBean("v1","v2","v3");
	}

}
