package com.nursing.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nursing.rest.model.Patient;


@Repository
public interface IPatientDao extends JpaRepository<Patient, String> {

}
