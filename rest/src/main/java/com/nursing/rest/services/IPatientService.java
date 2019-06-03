package com.nursing.rest.services;

import java.util.List;

import com.nursing.rest.model.*;

import org.springframework.data.util.Pair;

/**
 * IPatientService
 */
public interface IPatientService {

	public Patient save(Patient patient);
	
	public Patient update(Patient patient);

	public void delete(Patient patient);

	public List<Patient> findAll();

	// NOTE: Punto 1a)
	public List<Patient> findAllByName(String names);

	public List<Patient> findAllByLastName(String lastnames);
	
	public List<Pair<Patient, Integer>> findAllPatientsByDocument();

	public Patient findByDocument(String document);

	// NOTE: Punto 2a)

}