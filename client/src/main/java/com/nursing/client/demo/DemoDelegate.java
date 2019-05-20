package com.nursing.client.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nursing.client.model.Medicine;

@Controller
public class DemoDelegate {

	@Value("${host}")
	private String host;

	@Value("${host_basepath}")
	private String hostBasepath;

	private RestTemplate restTemplate = new RestTemplate();

	public void getCar(long id) {

//		String url = getUrlService();
//		System.out.println("url was-> " + url);
//		ResponseEntity<Medicine> entity = restTemplate.getForEntity(url + "/medicines/1", Medicine.class);
		// Car car = entity.getBody();
//		System.out.println("res->" + entity);
//		Medicine m = entity.getBody();
//		System.out.println("ent->" + m);

//		ResponseEntity<Response> response = restTemplate.getForEntity(url+"/medicines", Response.class);
//		Response r = response.getBody();
//		for(Medicine me : r.getMedicines()) {
//			System.out.println(me);
//		}

		// return car;
	}

	private String getUrlService() {
//		return "http://"+host+":"+port+hostBasepath;
		return "https://nursing-rest.herokuapp.com/api";
	}
	
	
	

}
