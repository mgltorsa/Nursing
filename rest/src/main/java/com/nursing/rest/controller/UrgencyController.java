package com.nursing.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import com.nursing.rest.model.Patient;
import com.nursing.rest.model.UrgencyAttention;
import com.nursing.rest.services.IUrgencyService;


import com.nursing.rest.dao.IUrgencyAttentionDao;

@RestController
public class UrgencyController {

	@Autowired
	private IUrgencyService service;

	//------------------------------------------------------------
	//CRUD
	//------------------------------------------------------------

	@PostMapping(value = "/api/urgency")
	public UrgencyAttention saveUrgency(@RequestBody UrgencyAttention urgencyAttention){
		return service.save(urgencyAttention);
	}

	@PutMapping(value = "/api/urgency")
	public UrgencyAttention updateUrgency(@RequestBody UrgencyAttention urgencyAttention){
		return service.update(urgencyAttention);
	}

	@DeleteMapping(value = "/api/urgency", params = "id")
	public void deleteUrgency(Long id){
		UrgencyAttention ua = service.findById(id);
		if(ua!=null)
			service.delete(ua);
	}

	//------------------------------------------------------------
	//CRUD
	//------------------------------------------------------------

	@GetMapping(value = "/api/urgencies")
	public List<UrgencyAttention> getAll(){
		return service.findAll();
	}

	@GetMapping(value = "/api/urgencies", params = "patientDocument")
	public List<UrgencyAttention> getAllByPatient(String patientDocument){
		return service.findByPatient(patientDocument);
	}

	@GetMapping(value = "/api/urgencies", params = {"since", "until"})
	public List<UrgencyAttention> getBetweenDates(LocalDate since, LocalDate until){
		return service.findBetweenDates(since, until);
	}

//	@GetMapping(value = "/api/urgencies", params = "quatity")
//	public List<Patient> getAtentionsMoreThanLastMonth(Long quantity){
//		return service.findByAttentionsMoreThanLastMonth(quantity);
//	}

}
