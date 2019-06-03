package com.nursing.client.delegate.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nursing.client.model.Supply;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SupplyDelegate implements IDelegateService<Long, Supply>, IDelegateServiceByDate<Supply>,
		IDelegateServiceByPatient<Supply>, IDelegateServicesByUrgency<Supply> {

	@Value("${host}")
	private String host;

	@Value("${protocol}")
	private String protocol;

	@Value("${host_basepath}")
	private String hostBasepath;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Supply save(Supply entity) {

		ResponseEntity<Supply> response = restTemplate.postForEntity(url() + "/supply", entity, Supply.class);

		if(response.getStatusCode()==HttpStatus.PRECONDITION_FAILED){
			throw new IllegalStateException("entity already exists");
		}

		return response.getBody();
	}

	@Override
	public Supply get(Long id) {

		if(id==null) {
			throw new IllegalArgumentException("id was null");
		}
		ResponseEntity<Supply> response = restTemplate.getForEntity(url() + "/supplies", Supply.class, id);

		if(response.getStatusCode()==HttpStatus.PRECONDITION_FAILED) {
			throw new IllegalStateException("entity doesn't exists");
		}
		
		return response.getBody();
	}

	@Override
	public void update(Supply entity) {
		if(entity.getConsecutive()==null) {
			throw new IllegalArgumentException("consecutive was null");
		}
		restTemplate.put(url() + "/supply", entity);
	}

	@Override
	public void delete(Long id) {
		if(id==null) {
			throw new IllegalArgumentException("id was null");
		}
		
		restTemplate.delete(url() + "/supply", id);
	}

	@Override
	public List<Supply> getAll() {
		ResponseEntity<List<Supply>> response = restTemplate.exchange(url() + "/supplies", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Supply>>() {
				});
		List<Supply> supplies = response.getBody();

		return supplies;
	}

	private String url() {
		return protocol + "://" + host + "/" + hostBasepath;
	}

	@Override
	public List<Supply> findByDate(Date date) {
		// url, method, requestType, parametrized type reference, uriVariables
		ResponseEntity<List<Supply>> response = restTemplate.exchange(url() + "/supplies", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Supply>>() {
				}, date);
		List<Supply> supplies = response.getBody();

		return supplies;
	}

	@Override
	public List<Supply> findByPatient(String document) {
		// url, method, requestType, parametrized type reference, uriVariables
		ResponseEntity<List<Supply>> response = restTemplate.exchange(url() + "/supplies", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Supply>>() {
				}, document);
		List<Supply> supplies = response.getBody();

		return supplies;
	}

	@Override
	public List<Supply> findByUrgency(Long consecutive) {
		ResponseEntity<List<Supply>> response = restTemplate.exchange(url() + "/supplies", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Supply>>() {
				}, consecutive);
		List<Supply> supplies = response.getBody();

		return supplies;
	}

}
