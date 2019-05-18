package com.nursing.rest.services;

import java.util.List;


import com.nursing.rest.dao.IInventaryDao;
import com.nursing.rest.model.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * InventaryService
 */
@Service
public class InventaryService implements IInventaryService {

	@Autowired
	private IInventaryDao dao;

	@Override
	public void save(InventaryMedicine inventaryMedicine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InventaryMedicine findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InventaryMedicine> findByMedicine(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(InventaryMedicine inventary) {
		// TODO Auto-generated method stub
		
	}

	

}