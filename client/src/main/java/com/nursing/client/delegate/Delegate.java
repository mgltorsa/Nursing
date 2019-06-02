package com.nursing.client.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nursing.client.delegate.services.IDelegateService;
import com.nursing.client.model.Patient;

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
	
	public <T,K> List<T> getAll(Class<T> clasz){
		if(clasz.equals(Patient.class)) {
			Patient p = new Patient("2", "migueñ", "Torres");
			List<Patient> pps = new ArrayList<>();
			pps.add(p);
			return (List<T>) pps;
		}else {
		IDelegateService<K, T> delegate = lookupService.lookupDelegateService(clasz);
		return delegate.getAll();
		}
	}

}
