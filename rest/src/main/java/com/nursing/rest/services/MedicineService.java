package com.nursing.rest.services;

import java.util.List;

import javax.annotation.PostConstruct;

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

	@PostConstruct
	public void init(){
		Medicine m = new Medicine(1l, "name-1", "genericName-1", "laboratory-1", "indications-1");
		Medicine m2 = new Medicine(2l, "name-2", "genericName-2", "laboratory-2", "indications-2");
		Medicine m3 = new Medicine(3l, "name-3", "genericName-3", "laboratory-3", "indications-3");
		Medicine m4 = new Medicine(4l, "name-4", "genericName-4", "laboratory-4", "indications-4");
		Medicine m5 = new Medicine(5l, "name-5", "genericName-5", "laboratory-5", "indications-5");

		dao.save(m);
		dao.save(m2);
		dao.save(m3);
		dao.save(m4);
		dao.save(m5);

	}

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