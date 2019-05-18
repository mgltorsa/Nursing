package com.nursing.rest.services;

import java.time.LocalDate;
import java.util.List;


import com.nursing.rest.dao.IUrgencyAttentionDao;
import com.nursing.rest.model.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UrgencyService
 */
@Service
public class UrgencyService implements IUrgencyService {

	@Autowired
	private IUrgencyAttentionDao dao;

	@Override
	public void saveOrUpdate(UrgencyAttention urgencyAttention) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UrgencyAttention> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UrgencyAttention> findByPatient(String document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UrgencyAttention urgency) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UrgencyAttention findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UrgencyAttention> findBetweenDates(LocalDate since, LocalDate until) {
		// TODO Auto-generated method stub
		return null;
	}

	

}