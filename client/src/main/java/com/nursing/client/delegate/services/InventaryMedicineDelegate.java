package com.nursing.client.delegate.services;

import java.net.URI;
import java.net.URISyntaxException;
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

import com.nursing.client.model.InventoryMedicine;

@Component
@Lazy
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InventaryMedicineDelegate implements IDelegateService<Long, InventoryMedicine> {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${host}")
	private String host;

	@Value("${protocol}")
	private String protocol;

	@Value("${host_basepath}")
	private String hostBasepath;

	
	@Override
	public InventoryMedicine save(InventoryMedicine entity) {
		
		
		
		URI url;
		try {
			url = new URI(url()+"/inventory");
			ResponseEntity<InventoryMedicine> response = restTemplate.postForEntity(url, entity, InventoryMedicine.class);
			
			if(response.getStatusCode()==HttpStatus.PRECONDITION_FAILED) {
				throw new IllegalArgumentException();
			}
			return response.getBody();

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InventoryMedicine get(Long id) {
		if(id==null) {
			throw new IllegalArgumentException("id was null");
		}
		
		try {
			URI uri = new URI(url()+"/inventories?id="+id);
			ResponseEntity<InventoryMedicine> response = restTemplate.getForEntity(uri, InventoryMedicine.class);
			
			if(response.getStatusCode()==HttpStatus.PRECONDITION_FAILED) {
				throw new IllegalStateException("precondition failed, object already exists");
			}
			return response.getBody();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(InventoryMedicine entity) {
		if(entity.getId()==null) {
			throw new IllegalArgumentException("id was null");
		}
		restTemplate.put(url()+"/inventory",entity);
	}

	@Override
	public void delete(Long id) {

		if(id==null) {
			throw new IllegalArgumentException();
		}
		URI url;
		try {
			url = new URI(url()+"/inventory?id="+id);
			restTemplate.delete(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<InventoryMedicine> getAll() {
		ResponseEntity<List<InventoryMedicine>> response = restTemplate.exchange(
				  url()+"/inventories",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<InventoryMedicine>>(){});
		List<InventoryMedicine> inventories = response.getBody();
		
		return inventories;
	}
	
	private String url() {

		return protocol + "://" + host + "/" + hostBasepath;
	}
	
}

