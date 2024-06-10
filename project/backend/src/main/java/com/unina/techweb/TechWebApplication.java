package com.unina.techweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.unina.techweb.controller",
		"com.unina.techweb.repository",
		"com.unina.techweb.service",
		"com.unina.techweb.config",
		"com.unina.techweb.middleware",
		"com.unina.techweb.exceptions"
})
public class TechWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechWebApplication.class, args);
	}

}
