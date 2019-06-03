package com.nursing.rest.services;

import java.util.List;

import com.nursing.rest.model.*;

/**
 * IMedicineService
 */
public interface IMedicineService {

    public Medicine save(Medicine medicine);
    
    public Medicine update(Medicine medicine);
    
    public void delete(Medicine medicine);

    public Medicine findById(Long id);
    
    public List<Medicine> findAll();
    
	public List<Medicine> findByName(String name);
	
	public List<Medicine> findByLaboratory(String laboratory);
	
    public List<Medicine> findByAdministrationType(String administrationType);
    
	List<Medicine> findByGenericName(String genericName);

    

}