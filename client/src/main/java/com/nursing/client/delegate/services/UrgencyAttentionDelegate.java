package com.nursing.client.delegate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nursing.client.model.UrgencyAttention;

@Component
@Lazy
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UrgencyAttentionDelegate implements IDelegateService<Long, UrgencyAttention>{

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public UrgencyAttention save(UrgencyAttention entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UrgencyAttention get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UrgencyAttention update(UrgencyAttention entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UrgencyAttention delete(Long entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UrgencyAttention> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
