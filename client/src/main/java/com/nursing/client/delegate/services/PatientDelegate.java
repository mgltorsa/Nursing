package com.nursing.client.delegate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nursing.client.model.Patient;

@Component
@Lazy
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PatientDelegate implements IDelegateService<String, Patient>{

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Patient save(Patient entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient update(Patient entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient delete(String entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
