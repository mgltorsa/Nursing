package com.nursing.rest.services;

import java.util.List;

import com.nursing.rest.model.*;

/**
 * IMedicineService
 */
public interface IMedicineService {

    public void saveOrUpdate(Medicine medicine);
    public void delete(Medicine medicine);

    public Medicine findById(Long id);
    public List<Medicine> findAll();
	public List<Medicine> findByName(String name);
	public List<Medicine> findByLaboratory(String laboratory);
    public List<Medicine> findByAdministrationType(String administrationType);
    
    //NOTE: Punto 2b)
    public List<Medicine> findByQuantityLessThan(Long quantity);
	List<Medicine> findByGenericName(String genericName);

    
}