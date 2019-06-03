package com.nursing.client.delegate.services;

import java.util.Date;
import java.util.List;

public interface IDelegateServiceByDate<T> {

	public List<T> findByDate(Date date);
}
