package com.nursing.rest.services;

import java.util.List;


import com.nursing.rest.dao.IMedicineDao;
import com.nursing.rest.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MedicineService
 */
@Service
public class MedicineService implements IMedicineService {

	@Autowired
	private IMedicineDao dao;

	@Override
	public void saveOrUpdate(Medicine medicine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Medicine medicine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Medicine findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicine> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicine> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicine> findByGenericName(String genericName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicine> findByLaboratory(String laboratory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicine> findByAdministrationType(String administrationType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicine> findByQuantityLessThan(Long quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	
}