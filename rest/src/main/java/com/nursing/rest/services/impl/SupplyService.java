package com.nursing.rest.services.impl;

import java.util.List;

import com.nursing.rest.dao.ISupplyDao;
import com.nursing.rest.model.*;
import com.nursing.rest.services.ISupplyService;

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
	public Supply save(Supply supply) {
		// TODO Auto-generated method stub
		return dao.save(supply);
	}

	@Override
	public Supply update(Supply supply) {
		// TODO Auto-generated method stub
		Supply s = dao.getOne(supply.getConsecutive());
		//Update
		if(s!=null){
			s.setDate(supply.getDate());
			s.setMedicine(supply.getMedicine());
			s.setObservations(supply.getObservations());
			s.setPathology(supply.getPathology());
			s.setPatient(supply.getPatient());
			s.setQuantity(supply.getQuantity());
			return dao.save(s);
		}else
			return null;
	}

	@Override
	public void delete(Supply supply) {
		// TODO Auto-generated method stub
		dao.delete(supply);
	}

	@Override
	public Supply findById(Long consecutive) {
		// TODO Auto-generated method stub
		return dao.findById(consecutive).get();
	}

	@Override
	public List<Supply> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<Supply> findByMedicineName(String name) {
		// TODO Auto-generated method stub
		return dao.findByMedicineName(name);
	}

	@Override
	public List<Supply> findByQuantityRange(int minimum, int maximum) {
		// TODO Auto-generated method stub
		return dao.findByQuantityRange(minimum, maximum);
	}

	@Override
	public List<Supply> findMedicineSupplies(Long id) {
		// TODO Auto-generated method stub
		return dao.getMedicineSupplies(id);
	}

	@Override
	public List<Supply> findByPatientName(String patientName) {
		// TODO Auto-generated method stub
		return dao.findByPatientName(patientName);
	}

	@Override
	public List<Supply> findByPatient(String patientDocument) {
		return dao.findByPatient(patientDocument);
	}

	@Override
	public List<Supply> findByUrgency(Long urgencyId) {
		return dao.findByUrgency(urgencyId);
	}

	@Override
	public List<Supply> findByQuantityLessThan(Long quantity) {
		// TODO Auto-generated method stub
		return dao.findByQuantityLessThan(quantity);
	}

}