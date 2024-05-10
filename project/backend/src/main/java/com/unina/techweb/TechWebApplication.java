package com.unina.techweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.unina.techweb.controller", "com.unina.techweb.repository", "com.unina.techweb.service" })
public class TechWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechWebApplication.class, args);
	}

}
