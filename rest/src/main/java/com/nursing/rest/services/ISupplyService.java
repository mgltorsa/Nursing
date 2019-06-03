package com.nursing.rest.services;

import java.util.List;

import com.nursing.rest.model.*;

/**
 * ISupplyService
 */
public interface ISupplyService {

    public Supply save(Supply supply);
    
    public Supply update(Supply supply);
    
	public void delete(Supply supply);
	
	public List<Supply> findByMedicineName(String name);
	
	public Supply findById(Long consecutive);
	
	public List<Supply> findAll();
	
	public List<Supply> findByQuantityRange(int minimum, int maximum);
	
	public List<Supply> findMedicineSupplies(Long id);
	
	public List<Supply> findByPatientName(String patientName);

	public List<Supply> findByPatient(String patientDocument);

	public List<Supply> findByUrgency(Long patientDocument);
	
	public List<Supply> findByQuantityLessThan(Long quantity);
    
}