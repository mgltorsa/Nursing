package com.nursing.rest.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nursing.rest.dao.IMedicineDao;
import com.nursing.rest.model.Medicine;
import com.nursing.rest.services.IMedicineService;

@RestController
public class MedicineController {

	@Autowired
	private IMedicineService medicineService;
	
	@GetMapping(value = "/medicines",produces = "application/json")
	public List<Medicine> getMedicines(){
		return medicineService.findAll();
	}
	
	
	@GetMapping(value = "/medicines", params = "name")
	public List<Medicine> getMedicinesByName(@RequestParam String name){
		return medicineService.findByName(name);
	}
	
	@GetMapping(value = "/medicines", params = "genericName")
	public List<Medicine> getMedicinesByGenericName(@RequestParam String genericName){
		return medicineService.findByGenericName(genericName);
	}
	
	@GetMapping(value = "/medicines", params = "lab")
	public List<Medicine> getMedicineByLab(@RequestParam String lab){
		return medicineService.findByLaboratory(lab);
	}
	
	@GetMapping(value = "/medicines", params = "administrationType")
	public List<Medicine> getMedicinesByAdministrationType(@RequestParam String administrationType){
		return medicineService.findByAdministrationType(administrationType);
	}
}
