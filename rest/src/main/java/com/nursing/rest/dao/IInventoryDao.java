package com.nursing.rest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nursing.rest.model.InventoryMedicine;
import com.nursing.rest.model.Medicine;

@Repository
public interface IInventoryDao extends JpaRepository<InventoryMedicine, Long> {

	List<InventoryMedicine> findByMedicine(Medicine medicine);
    
}
