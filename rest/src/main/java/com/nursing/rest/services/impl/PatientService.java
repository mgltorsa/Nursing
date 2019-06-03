package com.nursing.rest.services.impl;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;

import com.nursing.rest.dao.IPatientDao;
import com.nursing.rest.model.*;
import com.nursing.rest.services.IPatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

/**
 * PatientService
 */
@Service
public class PatientService implements IPatientService {

	@Autowired
	private IPatientDao dao;
	
	@PostConstruct
	public void initDefault() {
		Patient p1 = new Patient("1", "miguel", "torres");
		Patient p2 = new Patient("2", "mauricio", "hernandez");
		Patient p3 = new Patient("3","jose","diaz");
		
		Medicine m1 = new Medicine(6l, "name-6", "generic-n-6", "lab-6", "indi-6");
		Medicine m2 = new Medicine(7l, "name-7", "generic-n-7", "lab-7", "indi-7");

		
		UrgencyAttention u1 = new UrgencyAttention(p1, LocalDate.now(), "general-d", "proc", false);
		Supply s1 = new Supply(m1, 20, p1, LocalDate.now(), "path-6");
		u1.getSupplies().add(s1);
		
		p1.getAttentions().add(u1);
		
		UrgencyAttention u2 = new UrgencyAttention(p2, LocalDate.now(), "general-d-2", "proc-2", true);
		Supply s2 = new Supply(m2, 20, p2, LocalDate.now(), "path-7");
		u1.getSupplies().add(s2);
		
		p2.getAttentions().add(u2);
		
		save(p1);
		save(p2);
		save(p3);
	}

	@Override
	public Patient save(Patient patient) {
		// TODO Auto-generated method stub
		return dao.save(patient);
	}
	
	@Override
	public Patient update(Patient patient) {
		// TODO Auto-generated method stub
		Patient p = dao.findByDocument(patient.getDocument());
		if(p!=null){
			p.setNames(patient.getNames());
			p.setLastnames(patient.getLastnames());
			p.setAcademicProgram(patient.getAcademicProgram());
			p.setState(patient.getState());
			p.setAcademicDependency(patient.getAcademicDependency());
			return dao.save(p);
		}else
			return null;
	}	

	@Override
	public void delete(Patient patient) {
		// TODO Auto-generated method stub
		dao.delete(patient);
	}

	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<Patient> findAllByName(String names) {
		// TODO Auto-generated method stub
		return dao.findAllByNames(names);
	}

	@Override
	public List<Patient> findAllByLastName(String lastnames) {
		// TODO Auto-generated method stub
		return dao.findByLastnames(lastnames);
	}

	@Override
	public Patient findByDocument(String document) {
		// TODO Auto-generated method stub
		return dao.findByDocument(document);
	}

	@Override
	public List<Pair<Patient, Integer>> findAllPatientsByDocument() {
		// TODO Auto-generated method stub
		return null;
	}

}