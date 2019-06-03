package com.nursing.rest.services;

import java.util.List;

import com.nursing.rest.model.*;

/**
 * IInventaryService
 */
public interface IInventaryService {

    public void save(InventoryMedicine inventaryMedicine);
	public InventoryMedicine findById(Long id);
	public List<InventoryMedicine> findByMedicine(Long id);
    public void delete(InventoryMedicine inventary);
    
}