package com.nursing.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nursing.rest.dao.IUrgencyAttentionDao;

@RestController
public class UrgencyController {

	@Autowired
	private IUrgencyAttentionDao urgencyDAO;
}
