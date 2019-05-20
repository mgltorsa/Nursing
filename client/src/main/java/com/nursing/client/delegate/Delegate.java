package com.nursing.client.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nursing.client.delegate.services.IDelegateService;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Delegate {

	@Autowired
	private LookupService lookupService;
	
	public <T> T save(T entity, Class<T> clasz) {
		IDelegateService<?, T> delegate = lookupService.lookupDelegateService(clasz);
		return delegate.save(entity);
	}
	
	public <T> T update(T entity, Class<T> clasz) {
		IDelegateService<?, T> delegate = lookupService.lookupDelegateService(clasz);
		return delegate.update(entity);
	}
	
	public <T, K> T get(K id, Class<T> clasz) {
		IDelegateService<K, T> delegate = lookupService.lookupDelegateService(clasz);
		return delegate.get(id);
	}
	
	public <T, K> T delete(K id, Class<T> clasz) {
		IDelegateService<K, T> delegate = lookupService.lookupDelegateService(clasz);
		return delegate.delete(id);
	}
	
	public <T,K> List<T> getAll(Class<T> clasz){
		IDelegateService<K, T> delegate = lookupService.lookupDelegateService(clasz);
		return delegate.getAll();
	}

}
