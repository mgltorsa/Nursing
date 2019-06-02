package com.nursing.client.delegate.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nursing.client.model.Medicine;

@Component
@Lazy
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MedicineDelegate implements IDelegateService<Long, Medicine> {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${host}")
	private String host;

	@Value("${protocol}")
	private String protocol;

	@Value("${host_basepath}")
	private String hostBasepath;

	@Override
	public Medicine save(Medicine entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medicine get(Long id) {
		ResponseEntity<Medicine> response = restTemplate.getForEntity(url()+"/medicines/"+id, Medicine.class);
		return response.getBody();
	}

	@Override
	public void update(Medicine entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Long entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Medicine> getAll() {

		ResponseEntity<List<Medicine>> response = restTemplate.exchange(
				  url()+"/medicines",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Medicine>>(){});
		List<Medicine> medicines = response.getBody();
		
		return medicines;
	}

	

	private String url() {

		return protocol + "://" + host + "/" + hostBasepath;
	}

}
