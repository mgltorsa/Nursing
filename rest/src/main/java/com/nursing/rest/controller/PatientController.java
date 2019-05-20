package com.nursing.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nursing.rest.dao.IPatientDao;

@RestController
public class PatientController {
	
	@Autowired
	private IPatientDao patientDAO;
	
}
