package com.nursing.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nursing.rest.model.Patient;
import com.nursing.rest.services.IPatientService;

@RestController
public class PatientController {
	
	@Autowired
	private IPatientService patientService;
	
	//------------------------------------------------------------
	//CRUD
	//------------------------------------------------------------
	
	@PostMapping(value = "/api/patient")
	public Patient saveEntity(@RequestBody Patient patient) {
		return patientService.save(patient);
	}

	@PutMapping(value = "/api/patient")
	public Patient updateEntity(@RequestBody Patient patient) {
		return patientService.update(patient);
	}
	
	@GetMapping(value="/api/patients", params="document")
	public Patient getPatient(String document) {
		return patientService.findByDocument(document);
	}

	@DeleteMapping(value = "/api/patient", params = "id")
	public void deleteMedicine(String document) {
		Patient p = patientService.findByDocument(document);
		if(p!= null)
			patientService.delete(p);
	}

	//------------------------------------------------------------
	//CRUD
	//------------------------------------------------------------

	@GetMapping(value = "/api/patients", produces = "application/json")
	public List<Patient> getMedicines() {
		return patientService.findAll();
	}

	@GetMapping(value = "/api/patients", params = "name")
	public List<Patient> getPatientsByName(String name) {
		return patientService.findAllByName(name);
	}
	
	@GetMapping(value = "/api/patients", params = "lastname")
	public List<Patient> getPatientsByLastname(String lastname) {
		return patientService.findAllByName(lastname);
	}
	
}
