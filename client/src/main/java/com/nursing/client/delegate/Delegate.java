package com.nursing.client.delegate;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nursing.client.delegate.services.IDelegateService;
import com.nursing.client.delegate.services.IDelegateServiceByDate;
import com.nursing.client.delegate.services.IDelegateServiceByPatient;
import com.nursing.client.delegate.services.IDelegateServicesByUrgency;
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Delegate {

	@Autowired
	private LookupService lookupService;

	public <T> T save(T entity, Class<T> clasz) {
		IDelegateService<?, T> delegate = lookupService.lookupDelegateService(clasz);
		return delegate.save(entity);
	}

	public <T> void update(T entity, Class<T> clasz) {
		IDelegateService<?, T> delegate = lookupService.lookupDelegateService(clasz);
		delegate.update(entity);
	}

	public <T, K> T get(K id, Class<T> clasz) {
		IDelegateService<K, T> delegate = lookupService.lookupDelegateService(clasz);
		return delegate.get(id);
	}

	public <T, K> void delete(K id, Class<T> clasz) {
		IDelegateService<K, T> delegate = lookupService.lookupDelegateService(clasz);
		delegate.delete(id);
	}

	public <T, K> List<T> getAll(Class<T> clasz) {

		IDelegateService<K, T> delegate = lookupService.lookupDelegateService(clasz);
		return delegate.getAll();

	}

	public <T> List<T> findByDate(Date date, Class<T> clasz) {
		IDelegateServiceByDate<T> delegate= lookupService.lookupDelegateServiceByDate(clasz);
		return delegate.findByDate(date);
	}

	public <T> List<T> findByPatient(String document,
			Class<T> clasz) {
		IDelegateServiceByPatient<T> delegate= lookupService.lookupDelegateServiceByPatient(clasz);
		return delegate.findByPatient(document);
	}

	public <T> List<T> findByUrgency(Long consecutive, Class<T> clasz) {
		IDelegateServicesByUrgency<T> delegate= lookupService.lookupDelegateServiceByUrgency(clasz);
		return delegate.findByUrgency(consecutive);
	}

}
