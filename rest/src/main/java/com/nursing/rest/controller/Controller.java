package com.nursing.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nursing.rest.dao.IMedicineDao;
import com.nursing.rest.model.Medicine;

@RestController
public class Controller {

	@Autowired
	private IMedicineDao dao;
	
	@GetMapping(value = "/medicines",produces = "application/json")
	public List<Medicine> getMedicines(){
		return dao.findAll();
	}
}
