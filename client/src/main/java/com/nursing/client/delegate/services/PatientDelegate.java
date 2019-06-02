package com.nursing.client.delegate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
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
		
		ResponseEntity<Patient> response = restTemplate.postForEntity(url()+"/patients", entity, Patient.class);
		Patient patient = response.getBody();
		return patient;
	}

	@Override
	public Patient get(String id) {
		ResponseEntity<Patient> response = restTemplate.getForEntity(url()+"/patients/"+id, Patient.class);
		Patient patient = response.getBody();
		return patient;
	}

	@Override
	public void update(Patient entity) {
		restTemplate.put(url()+"/patient/"+entity.getDocument(),entity);
	}

	@Override
	public void delete(String entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Patient> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String url() {
		return protocol + "://" + host + "/" + hostBasepath;
	}

}
