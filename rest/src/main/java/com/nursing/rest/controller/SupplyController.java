package com.nursing.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nursing.rest.model.Supply;
import com.nursing.rest.services.ISupplyService;


@RestController
public class SupplyController {
	
	@Autowired
	private ISupplyService supplyService;
	
	//------------------------------------------------------------
	//CRUD
	//------------------------------------------------------------

	@PostMapping(value = "/api/supply")
	public Supply saveSupply(@RequestBody Supply supply) {
		return supplyService.save(supply);
	}
	
	@PutMapping(value = "/api/supply")
	public Supply updateSupply(@RequestBody Supply supply) {
		return supplyService.update(supply);
	}
	
	@DeleteMapping(value = "/api/supply", params = "id")
	public void deleteSupply(String id) {
		Supply s = supplyService.findById(Long.parseLong(id));
		if(s!=null)
			supplyService.delete(s);
	}

	//------------------------------------------------------------
	//CRUD
	//------------------------------------------------------------
	
	@GetMapping(value = "/api/supplies")
	public List<Supply> getSupplyAll() {
		return supplyService.findAll();
	}
	
	@GetMapping(value = "/api/supplies", params = "id")
	public Supply getSupplyById(String id) {
		return supplyService.findById(Long.parseLong(id));
	}
	
	@GetMapping(value = "/api/supplies", params = "name")
	public List<Supply> getSupplyByName(String name) {
		return supplyService.findByMedicineName(name);
	}

	@GetMapping(value = "/api/supplies", params = "document")
	public List<Supply> getPatientSuplies(String document) {
		return supplyService.findByPatient(document);
}
	
	@GetMapping(value = "/api/supplies", params = "min,max")
	public List<Supply> getSupplyByQuantityRange(String name) {
		return supplyService.findByMedicineName(name);
	}

	@GetMapping(value = "/api/supplies", params = "urgencyId")
	public List<Supply> getSupplyByQuantityRange(Long urgencyId) {
		return supplyService.findByUrgency(urgencyId);
	}

}
