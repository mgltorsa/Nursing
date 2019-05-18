package com.nursing.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nursing.rest.model.InventaryMedicine;

@Repository
public interface IInventaryDao extends JpaRepository<InventaryMedicine, Long> {    

    
}
