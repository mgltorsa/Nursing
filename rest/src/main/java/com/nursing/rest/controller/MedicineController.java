package com.nursing.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nursing.rest.model.Medicine;
import com.nursing.rest.services.IMedicineService;

@RestController
public class MedicineController {

	@Autowired
	private IMedicineService medicineService;

	@GetMapping(value = "/api/medicines", produces = "application/json")
	public List<Medicine> getMedicines() {
		return medicineService.findAll();
	}
	
	@GetMapping(value="/api/medicines", params = "id")
	public Medicine getMedicinesByName(Long id) {
		return medicineService.findById(id);
	}
	
	@GetMapping(value = "/api/medicines", params = "name")
	public List<Medicine> getMedicinesByName(String name) {
		return medicineService.findByName(name);
	}

	@GetMapping(value = "/api/medicines", params = "genericName")
	public List<Medicine> getMedicinesByGenericName(String genericName) {
		return medicineService.findByGenericName(genericName);
	}

	@GetMapping(value = "/api/medicines", params = "lab")																																																																																																
	public List<Medicine> getMedicineByLab(String lab) {
		return medicineService.findByLaboratory(lab);
	}

	@GetMapping(value = "/api/medicines", params = "administrationType")
	public List<Medicine> getMedicinesByAdministrationType(String administrationType) {
		return medicineService.findByAdministrationType(administrationType);
	}

	//------------------------------------------------------------
	//CRUD
	//------------------------------------------------------------

	@PostMapping(value = "/api/medicine")
	public Medicine saveEntity(@RequestBody Medicine medicine) {
		return medicineService.save(medicine);
	}

	@PutMapping(value = "/api/medicine")
	public Medicine updateEntity(@RequestBody Medicine medicine) {
		return medicineService.update(medicine);
	}
	
	@DeleteMapping(value = "/api/medicine", params = "id")
	public void deleteMedicine(String id) {
		Medicine m = medicineService.findById(Long.parseLong(id));
		if(m!= null)
			medicineService.delete(m);
	}

	//------------------------------------------------------------
	//CRUD
	//------------------------------------------------------------
}
