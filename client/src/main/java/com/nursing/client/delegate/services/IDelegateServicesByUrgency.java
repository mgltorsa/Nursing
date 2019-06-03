package com.nursing.client.delegate.services;

import java.util.List;

public interface IDelegateServicesByUrgency<T> {

	public List<T> findByUrgency(Long consecutive);
}
