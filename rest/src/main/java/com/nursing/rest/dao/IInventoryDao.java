package com.nursing.rest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nursing.rest.model.InventoryMedicine;
import com.nursing.rest.model.Medicine;
import com.nursing.rest.model.Supply;

@Repository
public interface IInventoryDao extends JpaRepository<InventoryMedicine, Long> {    

	@Query("SELECT im FROM InventoryMedicine im WHERE im.medicine.consecutive = ?1 ")
	List<InventoryMedicine> getMedicineSupplies(Long medicine);
	
}
