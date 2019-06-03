package com.nursing.client.delegate.services;

import java.util.List;

public interface IDelegateService<K,T> {

	public T save(T entity);
	public T get(K id);
	public void update(T entity);
	public void delete(K entity);
	public List<T> getAll();
}
