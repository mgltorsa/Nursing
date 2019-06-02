package com.nursing.client.delegate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nursing.client.model.Supply;

@Component
@Lazy
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SupplyDelegate implements IDelegateService<Long, Supply> {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Supply save(Supply entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supply get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Supply entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Long entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Supply> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
