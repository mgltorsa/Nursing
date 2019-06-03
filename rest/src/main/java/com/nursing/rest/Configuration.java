package com.nursing.rest;

import org.springframework.context.annotation.Bean;

import com.nursing.rest.services.IMedicineService;
import com.nursing.rest.services.impl.MedicineService;

//@org.springframework.context.annotation.Configuration
public class Configuration {
	
	@Bean
	public IMedicineService medicineService() {
		return new MedicineService();
	}
}
