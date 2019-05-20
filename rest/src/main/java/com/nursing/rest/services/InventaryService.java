package com.nursing.rest.services;

import java.util.List;


import com.nursing.rest.dao.IInventoryDao;
import com.nursing.rest.model.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * InventaryService
 */
@Service
public class InventaryService implements IInventaryService {

	@Autowired
	private IInventoryDao dao;

	@Override
	public void save(InventoryMedicine inventaryMedicine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InventoryMedicine findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InventoryMedicine> findByMedicine(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(InventoryMedicine inventary) {
		// TODO Auto-generated method stub
		
	}

	

}