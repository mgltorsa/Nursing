package com.nursing.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nursing.rest.model.InventoryMedicine;

@Repository
public interface IInventoryDao extends JpaRepository<InventoryMedicine, Long> {    

    
}
