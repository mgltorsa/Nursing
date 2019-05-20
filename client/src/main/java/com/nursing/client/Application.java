package com.nursing.client;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import com.nursing.client.demo.DemoDelegate;

@SpringBootApplication
public class Application {
	
	private static DemoDelegate delegate = new DemoDelegate();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		delegate.getCar(1);
	}
	
	@Bean
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
