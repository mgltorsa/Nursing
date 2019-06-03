package com.nursing.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nursing.rest.model.InventoryMedicine;
import com.nursing.rest.model.Medicine;
import com.nursing.rest.services.IInventoryService;
import com.nursing.rest.services.IMedicineService;

@RestController
public class InventoryController {

	@Autowired
	private IInventoryService inventoryService;
	
	@Autowired
	private IMedicineService medicineService;

	//------------------------------------------------------------
	//CRUD
	//------------------------------------------------------------

	@PostMapping(value = "/api/inventory")
	public InventoryMedicine saveInventoy(@RequestBody InventoryMedicine inventory) {
		return inventoryService.save(inventory);
	}
	
	@PostMapping(value = "/api/inventory",params = "id")
	public InventoryMedicine saveInventory(@RequestBody InventoryMedicine inventory, Long id) {
		Medicine medicine = medicineService.findById(id);
		inventory.setMedicine(medicine);
		inventoryService.save(inventory);
		return inventoryService.findById(inventory.getId());
	}
	
	@GetMapping(value="/api/inventories", params="id")
	public InventoryMedicine getInventory(Long id) {
		return inventoryService.findById(id);
	}


	@PutMapping(value = "/api/inventory")
	public InventoryMedicine updateInventoy(@RequestBody InventoryMedicine inventory) {
		return inventoryService.update(inventory);
	}

	@DeleteMapping(value = "/api/inventory", params =  "id")
	public ResponseEntity<?> deleteInventory(Long id) {
		InventoryMedicine im = inventoryService.findById(id);
		if(im != null) {
			inventoryService.delete(im);
		}
		
		ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.OK);
		return response;
	}

	//------------------------------------------------------------
	//CRUD
	//------------------------------------------------------------

	@GetMapping(value = "/api/inventoriesByMedicine", params = "medicineId")
	public List<InventoryMedicine> getMedicineInventories(Long medicineId){
		return inventoryService.findMedicineInventories(medicineId);
	}
	
	@GetMapping(value = "/api/inventories")
	public List<InventoryMedicine> getMedicineInventories() {
		return inventoryService.findAll();
	}

}

