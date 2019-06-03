package com.nursing.rest.services;

import java.util.List;

import com.nursing.rest.model.*;

/**
 * IInventaryService
 */
public interface IInventoryService {

    public InventoryMedicine save(InventoryMedicine inventaryMedicine);
    
    public InventoryMedicine update(InventoryMedicine inventaryMedicine);
    
    public void delete(InventoryMedicine inventory);
    
	public InventoryMedicine findById(Long id);
	
    public List<InventoryMedicine> findMedicineInventories(Long medicineId);

	public List<InventoryMedicine> findAll();
}