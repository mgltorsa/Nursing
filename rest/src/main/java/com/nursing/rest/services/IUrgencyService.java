package com.nursing.rest.services;

import java.time.LocalDate;
import java.util.List;

import com.nursing.rest.model.*;

/**
 * IUrgencyService
 */
public interface IUrgencyService {

	public UrgencyAttention save(UrgencyAttention urgencyAttention);

	public UrgencyAttention update(UrgencyAttention urgencyAttention);

	public void delete(UrgencyAttention urgency);

	public UrgencyAttention findById(Long id);

	public List<UrgencyAttention> findAll();

	public List<UrgencyAttention> findByPatient(String document);

	public List<UrgencyAttention> findBetweenDates(LocalDate since, LocalDate until);

//	public List<Patient> findByAttentionsMoreThanLastMonth(Long quantity);
	
}