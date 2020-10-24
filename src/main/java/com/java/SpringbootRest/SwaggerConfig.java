package com.java.SpringbootRest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("Mahesh Bawage", "mehesh.com", "98900957257");

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Greate Api Title", "Api Documentation", "1.0",
			"urn:Terms of services", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
	private static final Set<String> PRODUCES_AND_CONSUMES = new HashSet<String>(
			Arrays.asList("application/xml", "application/json"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO).produces(PRODUCES_AND_CONSUMES);
	}

}
