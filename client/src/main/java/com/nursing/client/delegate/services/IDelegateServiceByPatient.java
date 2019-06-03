package com.nursing.client.delegate.services;

import java.util.List;

public interface IDelegateServiceByPatient<T> {

	public List<T> findByPatient(String document);
}
