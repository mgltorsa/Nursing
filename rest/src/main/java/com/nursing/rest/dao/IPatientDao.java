package com.nursing.rest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nursing.rest.model.Patient;


@Repository
public interface IPatientDao extends JpaRepository<Patient, String> {
	
	public List<Patient> findAllByNames(String name);
	
	public List<Patient> findByLastnames(String lastnames);
	
	public List<Patient> findAllByDocument(String document);
	
	public Patient findByDocument(String document);
	
}
