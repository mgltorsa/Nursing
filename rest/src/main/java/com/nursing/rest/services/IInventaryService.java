package com.nursing.rest.services;

import java.util.List;

import com.nursing.rest.model.*;

/**
 * IInventaryService
 */
public interface IInventaryService {

    public void save(InventaryMedicine inventaryMedicine);
	public InventaryMedicine findById(Long id);
	public List<InventaryMedicine> findByMedicine(Long id);
    public void delete(InventaryMedicine inventary);
    
}