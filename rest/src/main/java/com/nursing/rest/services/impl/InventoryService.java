package com.nursing.rest.services.impl;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.type.LocalDateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursing.rest.dao.IInventoryDao;
import com.nursing.rest.model.InventoryMedicine;
import com.nursing.rest.model.Medicine;
import com.nursing.rest.services.IInventoryService;

@Service
public class InventoryService implements IInventoryService{
	
	@Autowired
	private IInventoryDao inventory;

	@Override
	public InventoryMedicine save(InventoryMedicine inventaryMedicine) {
		// TODO Auto-generated method stub
		return inventory.save(inventaryMedicine);
	}

	@Override
	public InventoryMedicine update(InventoryMedicine inventaryMedicine) {
		// TODO Auto-generated method stub
		InventoryMedicine im = inventory.getOne(inventaryMedicine.getId());
		//Replace atributes
		if(im!=null){
			im.setAvailableQuantity(inventaryMedicine.getAvailableQuantity());
			im.setExpirationDate(inventaryMedicine.getExpirationDate());
			im.setUbication(inventaryMedicine.getUbication());
			im.setMedicine(inventaryMedicine.getMedicine());
			return inventory.save(im);
		}else
			return null;
	}

	@Override
	public void delete(InventoryMedicine inventaryMedicine) {
		// TODO Auto-generated method stub
		inventory.delete(inventaryMedicine);
	}

	@Override
	public InventoryMedicine findById(Long id) {
		// TODO Auto-generated method stub
		return inventory.getOne(id);
	}

	@Override
	public List<InventoryMedicine> findMedicineInventories(Long medicineId) {
		// TODO Auto-generated method stub
		return inventory.getMedicineSupplies(medicineId);
	}

	@Override
	public List<InventoryMedicine> findAll() {
		// TODO Auto-generated method stub
		return inventory.findAll();
	}

}
