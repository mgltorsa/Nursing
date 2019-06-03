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

import com.nursing.client.model.Patient;

@Component
@Lazy
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PatientDelegate implements IDelegateService<String, Patient>{

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${host}")
	private String host;

	@Value("${protocol}")
	private String protocol;

	@Value("${host_basepath}")
	private String hostBasepath;
	
	@Override
	public Patient save(Patient entity) {
		
		ResponseEntity<Patient> response = restTemplate.postForEntity(url()+"/patient", entity, Patient.class);
		
		if(response.getStatusCode()==HttpStatus.PRECONDITION_FAILED) {
			throw new IllegalStateException("patient already exists");
		}
		Patient patient = response.getBody();
		return patient;
	}

	@Override
	public Patient get(String id) {
		if(id==null) {
			throw new IllegalArgumentException("id was null");
		}
		ResponseEntity<Patient> response = restTemplate.getForEntity(url()+"/patients", Patient.class, id);
		if(response.getStatusCode()==HttpStatus.PRECONDITION_FAILED) {
			throw new IllegalArgumentException();
		}
		Patient patient = response.getBody();
		return patient;
	}

	@Override
	public void update(Patient entity) {
		if(entity.getDocument()==null) {
			throw new IllegalArgumentException();
		}
		restTemplate.put(url()+"/patient?id="+entity.getDocument(),entity);
	}

	@Override
	public void delete(String id) {
		if(id==null) {
			throw new IllegalArgumentException("id was null");
		}
		restTemplate.delete(url()+"/patient",id);
	}

	@Override
	public List<Patient> getAll() {
		ResponseEntity<List<Patient>> response = restTemplate.exchange(
				  url()+"/patients",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Patient>>(){});
		List<Patient> patients = response.getBody();
		
		return patients;
	}
	
	private String url() {
		return protocol + "://" + host + "/" + hostBasepath;
	}

}
