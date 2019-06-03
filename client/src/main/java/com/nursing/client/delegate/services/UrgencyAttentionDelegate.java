package com.nursing.client.delegate.services;

import java.util.Date;
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

import com.nursing.client.model.UrgencyAttention;

@Component
@Lazy
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UrgencyAttentionDelegate implements IDelegateService<Long, UrgencyAttention>,
		IDelegateServiceByDate<UrgencyAttention>, IDelegateServiceByPatient<UrgencyAttention> {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${host}")
	private String host;

	@Value("${protocol}")
	private String protocol;

	@Value("${host_basepath}")
	private String hostBasepath;

	@Override
	public UrgencyAttention save(UrgencyAttention entity) {
		ResponseEntity<UrgencyAttention> response = restTemplate.postForEntity(url() + "/urgency", entity,
				UrgencyAttention.class);
		
		if(response.getStatusCode()==HttpStatus.PRECONDITION_FAILED) {
			throw new IllegalArgumentException("attentio already exists");
		}
		return response.getBody();
	}

	@Override
	public UrgencyAttention get(Long id) {
		
		if(id==null) {
			throw new IllegalArgumentException("id was null");
		}
		ResponseEntity<UrgencyAttention> response = restTemplate.getForEntity(url() + "/urgencies",
				UrgencyAttention.class, id);
		
		if(response.getStatusCode()==HttpStatus.PRECONDITION_FAILED) {
			throw new IllegalStateException("attentiond doesn't exists");
		}
		return response.getBody();
	}

	@Override
	public void update(UrgencyAttention entity) {
		if(entity.getConsecutive()==null) {
			throw new IllegalArgumentException("id was null");
		}
		restTemplate.put(url() + "/urgency", entity, UrgencyAttention.class);
	}

	@Override
	public void delete(Long id) {
		if(id==null) {
			throw new IllegalArgumentException("id was null");
		}
		restTemplate.delete(url() + "/urgency", id);
	}

	@Override
	public List<UrgencyAttention> getAll() {
		ResponseEntity<List<UrgencyAttention>> response = restTemplate.exchange(url() + "/urgencies", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<UrgencyAttention>>() {
				});
		List<UrgencyAttention> urgencies = response.getBody();

		return urgencies;
	}

	private String url() {

		return protocol + "://" + host + "/" + hostBasepath;
	}

	@Override
	public List<UrgencyAttention> findByDate(Date date) {
		// url, method, requestType, parametrized type reference, uriVariables
		ResponseEntity<List<UrgencyAttention>> response = restTemplate.exchange(url() + "/urgencies", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<UrgencyAttention>>() {
				}, date);
		List<UrgencyAttention> urgencies = response.getBody();

		return urgencies;
	}

	@Override
	public List<UrgencyAttention> findByPatient(String document) {
		// url, method, requestType, parametrized type reference, uriVariables
		ResponseEntity<List<UrgencyAttention>> response = restTemplate.exchange(url() + "/urgencies", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<UrgencyAttention>>() {
				}, document);
		List<UrgencyAttention> urgencies = response.getBody();

		return urgencies;
	}

}
