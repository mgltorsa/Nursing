package com.nursing.rest.services.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import com.nursing.rest.dao.IUrgencyAttentionDao;
import com.nursing.rest.model.*;
import com.nursing.rest.services.IUrgencyService;

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
	public UrgencyAttention save(UrgencyAttention urgencyAttention) {
		// TODO Auto-generated method stub
		return dao.save(urgencyAttention);
	}

	@Override
	public UrgencyAttention update(UrgencyAttention urgencyAttention) {
		// TODO Auto-generated method stub
		UrgencyAttention ua = dao.getOne(urgencyAttention.getConsecutive());
		//Replace atributes
		if(ua!=null){
			ua.setDate(urgencyAttention.getDate());
			ua.setForwarded(urgencyAttention.getForwarded());
			ua.setPatient(urgencyAttention.getPatient());
			ua.setGeneralDescription(urgencyAttention.getGeneralDescription());
			ua.setProcedure(urgencyAttention.getProcedure());
			ua.setForwardedPlace(urgencyAttention.getForwardedPlace());
		}
		return dao.save(ua);
	}

	@Override
	public void delete(UrgencyAttention urgency) {
		// TODO Auto-generated method stub
		dao.delete(urgency);
	}

	@Override
	public List<UrgencyAttention> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public UrgencyAttention findById(Long id) {
		// TODO Auto-generated method stub
		return dao.getOne(id);
	}

	@Override
	public List<UrgencyAttention> findByPatient(String document) {
		// TODO Auto-generated method stub
		return dao.findByPatientDocument(document);
	}

	@Override
	public List<UrgencyAttention> findBetweenDates(LocalDate since, LocalDate until) {
		// TODO Auto-generated method stub
		return dao.findBetweenDates(since, until);
	}

//	@Override
//	public List<Patient> findByAttentionsMoreThanLastMonth(Long quantity) {
//		// TODO Auto-generated method stub
//		
//		LocalDate since = LocalDate.now().minus(1, ChronoUnit.MONTHS);
//		LocalDate ultil = LocalDate.now();	
//		return dao.findBetweenDatesByAmount(since, ultil, quantity);
//		return null;
//	}
}