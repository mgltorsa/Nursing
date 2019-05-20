package com.nursing.rest.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@GetMapping(value = "/medicines/{name}")
	public List<Medicine> getMedicinesByName(@PathVariable String name){
		return medicineService.findByName(name);
	}
	
	@GetMapping(value = "/medicines/gn-{genericName}")
	public List<Medicine> getMedicinesByGenericName(@PathVariable String genericName){
		return medicineService.findByGenericName(genericName);
	}
	
	@GetMapping("/medicines/lb-{lab}")
	public List<Medicine> getMedicineByLab(@PathVariable String lab){
		return medicineService.findByLaboratory(lab);
	}
	
	@GetMapping("/medicines/at-{administrationType}")
	public List<Medicine> getMedicinesByAdministrationType(@PathVariable String administrationType){
		return medicineService.findByAdministrationType(administrationType);
	}
}
