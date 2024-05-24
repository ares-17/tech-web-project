package com.unina.techweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = { "com.unina.techweb.controller", "com.unina.techweb.repository", "com.unina.techweb.service", "com.unina.techweb.config"})
@CrossOrigin("localhost:5173")
public class TechWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechWebApplication.class, args);
	}

}
