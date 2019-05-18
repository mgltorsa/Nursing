package com.nursing.rest.services;

import java.util.List;


import com.nursing.rest.dao.IPatientDao;
import com.nursing.rest.model.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

/**
 * PatientService
 */
@Service
public class PatientService implements IPatientService {

	@Autowired
	private IPatientDao dao;

	@Override
	public void saveOrUpdate(Patient patient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Patient patient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> findByName(String names) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> findByLastName(String lastnames) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient findByDocument(String document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pair<Patient, Integer>> findAllPatientsByDocument() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> findByAttentionsMoreThanLastMonth(Long quantity) {
		// TODO Auto-generated method stub
		return null;
	}


}