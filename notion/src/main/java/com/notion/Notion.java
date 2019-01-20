package com.notion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.notion.Notion;


@SpringBootApplication
public class Notion extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Notion.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Notion.class, args);
	}
}
