package com.nursing.client.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursing.client.delegate.services.IDelegateService;
import com.nursing.client.delegate.services.IDelegateServiceByDate;
import com.nursing.client.delegate.services.IDelegateServiceByPatient;
import com.nursing.client.delegate.services.IDelegateServicesByUrgency;
import com.nursing.client.delegate.services.InventaryMedicineDelegate;
import com.nursing.client.delegate.services.MedicineDelegate;
import com.nursing.client.delegate.services.PatientDelegate;
import com.nursing.client.delegate.services.SupplyDelegate;
import com.nursing.client.delegate.services.UrgencyAttentionDelegate;
import com.nursing.client.model.InventoryMedicine;
import com.nursing.client.model.Medicine;
import com.nursing.client.model.Patient;
import com.nursing.client.model.Supply;
import com.nursing.client.model.UrgencyAttention;

@Service
public class LookupService {
	
	@Autowired
	private MedicineDelegate medicineDelegate;
	
	@Autowired
	private PatientDelegate patientDelegate;
	
	@Autowired
	private SupplyDelegate supplyDelegate;
	
	@Autowired
	private InventaryMedicineDelegate inventaryDelegate;
	
	@Autowired
	private UrgencyAttentionDelegate urgencyDelegate;

	@SuppressWarnings("unchecked")
	public <K, T> IDelegateService<K, T> lookupDelegateService(Class<T> clasz){
		
		IDelegateService<K, T> service = null;
		if(clasz.equals(Medicine.class)) {			
			service = (IDelegateService<K, T>) medicineDelegate;
		}else if(clasz.equals(Patient.class)) {
			service = (IDelegateService<K, T>) patientDelegate;
		}else if(clasz.equals(Supply.class)) {
			service = (IDelegateService<K, T>) supplyDelegate;
		}else if(clasz.equals(InventoryMedicine.class)) {
			service = (IDelegateService<K, T>) inventaryDelegate;
		}else if(clasz.equals(UrgencyAttention.class)) {
			service = (IDelegateService<K, T>) urgencyDelegate;
		}else {
			throw new UnsupportedOperationException("not implemented for another class");
		}
		
		return service;		
	}

	@SuppressWarnings("unchecked")
	public <T> IDelegateServiceByDate<T> lookupDelegateServiceByDate(Class<T> clasz) {
		
		IDelegateServiceByDate<T> service = null;

		if(clasz.equals(UrgencyAttention.class)) {
			service = (IDelegateServiceByDate<T>) urgencyDelegate;
		}else if(clasz.equals(Supply.class)) {
			service = (IDelegateServiceByDate<T>) supplyDelegate;
		}else {
			throw new UnsupportedOperationException("not implemented for another class");
		}
		return service;
	}

	@SuppressWarnings("unchecked")
	public <T> IDelegateServiceByPatient<T> lookupDelegateServiceByPatient(Class<T> clasz) {
		IDelegateServiceByPatient<T> service = null;

		if(clasz.equals(UrgencyAttention.class)) {
			service = (IDelegateServiceByPatient<T>) urgencyDelegate;
		}else if(clasz.equals(Supply.class)) {
			service = (IDelegateServiceByPatient<T>) supplyDelegate;
		}else {
			throw new UnsupportedOperationException("not implemented for another class");
		}
		return service;
	}

	@SuppressWarnings("unchecked")
	public <T> IDelegateServicesByUrgency<T> lookupDelegateServiceByUrgency(Class<T> clasz) {
		return (IDelegateServicesByUrgency<T>) supplyDelegate;
	}
}
