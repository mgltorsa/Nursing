package com.nursing.client.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursing.client.delegate.services.IDelegateService;
import com.nursing.client.delegate.services.InventaryMedicineDelegate;
import com.nursing.client.delegate.services.MedicineDelegate;
import com.nursing.client.delegate.services.PatientDelegate;
import com.nursing.client.delegate.services.SupplyDelegate;
import com.nursing.client.delegate.services.UrgencyAttentionDelegate;
import com.nursing.client.model.InventaryMedicine;
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
		}else if(clasz.equals(InventaryMedicine.class)) {
			service = (IDelegateService<K, T>) inventaryDelegate;
		}else if(clasz.equals(UrgencyAttention.class)) {
			service = (IDelegateService<K, T>) urgencyDelegate;
		}
		
		return service;		
	}
}
