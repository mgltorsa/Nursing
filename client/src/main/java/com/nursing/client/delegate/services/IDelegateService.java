package com.nursing.client.delegate.services;

import java.util.List;

public interface IDelegateService<K,T> {

	public T save(T entity);
	public T get(K id);
	public T update(T entity);
	public T delete(K entity);
	public List<T> getAll();
}
