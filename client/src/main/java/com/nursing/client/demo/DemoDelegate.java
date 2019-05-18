package com.nursing.client.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class DemoDelegate {

	@Value("${host}")
	private String host;
	
	@Value("${host_basepath}")
	private String hostBasepath;
	
	@Value("${host_port}")
	private int port;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public void getCar(long id) {
		
		String url = getUrlService();
		System.out.println("url was-> "+url);
		// ResponseEntity<Car> entity = restTemplate.getForEntity(url+"/cars/1", Car.class);
		// Car car = entity.getBody();
		
		// return car;
	}

	private String getUrlService() {
//		return "http://"+host+":"+port+hostBasepath;
		return "http://localhost:8080/api";
	}
	
}
