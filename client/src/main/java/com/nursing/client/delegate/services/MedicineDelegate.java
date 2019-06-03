package com.nursing.client.delegate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
		if(entity==null) {
			throw new IllegalArgumentException("entity was null");
		}
		ResponseEntity<Medicine> response =  restTemplate.postForEntity(url()+"/medicine", entity, Medicine.class);
		if(response.getStatusCode()==HttpStatus.PRECONDITION_FAILED) {
			throw new IllegalArgumentException("entity already exists");
		}
		return response.getBody();
	}

	@Override
	public Medicine get(Long id) {
		if(id==null) {
			throw new IllegalArgumentException("id was null");
		}
		ResponseEntity<Medicine> response = restTemplate.getForEntity(url()+"/medicines", Medicine.class,id);
		if(response.getStatusCode()==HttpStatus.PRECONDITION_FAILED) {
			throw new IllegalArgumentException("medicine doesn't exists");
		}
		return response.getBody();
	}

	@Override
	public void update(Medicine entity) {
		if(entity.getConsecutive()==null) {
			throw new IllegalArgumentException("consecutive was null");
		}
		restTemplate.put(url()+"/medicine", entity);
	}

	@Override
	public void delete(Long id) {
		if(id==null) {
			throw new IllegalArgumentException("id was null");
		}
		restTemplate.delete(url()+"/medicine",id);
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
