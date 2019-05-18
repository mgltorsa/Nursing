package com.nursing.rest.services;

import java.util.List;


import com.nursing.rest.dao.ISupplyDao;
import com.nursing.rest.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SupplyService
 */
@Service
public class SupplyService implements ISupplyService {

	@Autowired
	private ISupplyDao dao;

	@Override
	public void saveOrUpdate(Supply supply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Supply supply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Supply> findByMedicineName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supply findById(Long consecutive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Supply> findByQuantityRange(int minimum, int maximum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Supply> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	

}