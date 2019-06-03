package com.nursing.rest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nursing.rest.model.Medicine;

@Component
public class TestRestAPI {
	
	private RestTemplate template = new RestTemplate();
	
	public void addMedicine() {
		
		Medicine m = new Medicine(Long.valueOf("2"), "M", "M", "M", "M");
		template.postForEntity("https://nursing-rest.herokuapp.com/api/medicine", m, Medicine.class);
	}

}
