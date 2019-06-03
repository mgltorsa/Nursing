package com.nursing.rest.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.nursing.rest.dao.IMedicineDao;
import com.nursing.rest.model.*;
import com.nursing.rest.services.IInventoryService;
import com.nursing.rest.services.IMedicineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MedicineService
 */
@Service
public class MedicineService implements IMedicineService {

	@Autowired
	private IMedicineDao dao;

	@Autowired
	private IInventoryService inventory;

	@PostConstruct
	public void init() {
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

		InventoryMedicine im = new InventoryMedicine(m, 10, "Hospital", LocalDate.now());
		inventory.save(im);
	}

	@Override
	public Medicine save(Medicine medicine) {
		// TODO Auto-generated method stub
		return dao.save(medicine);
	}

	@Override
	public void delete(Medicine medicine) {
		// TODO Auto-generated method stub
		dao.delete(medicine);
	}

	@Override
	public Medicine update(Medicine medicine) {
		// TODO Auto-generated method stub
		Medicine m = dao.findById(medicine.getConsecutive()).get();
		if (m != null) {
			m.setName(medicine.getName());
			m.setGenericName(medicine.getGenericName());
			m.setLaboratory(medicine.getLaboratory());
			m.setAdministrationType(medicine.getAdministrationType());
			m.setIndications(medicine.getIndications());
			m.setContraIndications(medicine.getContraIndications());
			return dao.save(m);
		} else
			return null;
	}

	@Override
	public Medicine findById(Long id) {
		// TODO Auto-generated method stub
		return dao.findByConsecutive(id);
	}

	@Override
	public List<Medicine> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<Medicine> findByName(String name) {
		// TODO Auto-generated method stub
		return dao.findByName(name);
	}

	@Override
	public List<Medicine> findByGenericName(String genericName) {
		// TODO Auto-generated method stub
		return dao.findByGenericName(genericName);
	}

	@Override
	public List<Medicine> findByLaboratory(String laboratory) {
		// TODO Auto-generated method stub
		return dao.findByLaboratory(laboratory);
	}

	@Override
	public List<Medicine> findByAdministrationType(String administrationType) {
		// TODO Auto-generated method stub
		return dao.findByAdministrationType(administrationType);
	}

}