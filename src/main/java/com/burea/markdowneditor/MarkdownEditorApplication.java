package com.burea.markdowneditor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class MarkdownEditorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkdownEditorApplication.class, args);
	}

	public InternalResourceViewResolver addResourceHandlers() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/public/");
		resolver.setSuffix(".html");
		return resolver;
	}

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("America/Santiago"));
	}

}
