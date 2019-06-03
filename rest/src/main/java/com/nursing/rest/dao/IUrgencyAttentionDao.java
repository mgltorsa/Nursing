package com.nursing.rest.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nursing.rest.model.InventoryMedicine;
import com.nursing.rest.model.Patient;
import com.nursing.rest.model.UrgencyAttention;

@Repository
public interface IUrgencyAttentionDao extends JpaRepository<UrgencyAttention, Long>{

	@Query("SELECT u FROM UrgencyAttention u WHERE u.patient.document = ?1")
	public List<UrgencyAttention> findByPatientDocument(String document);
	
	@Query("SELECT u FROM UrgencyAttention u WHERE u.date BETWEEN ?1 AND ?2")
	public List<UrgencyAttention> findBetweenDates(LocalDate date1, LocalDate date2);
	
//	@Query("SELECT p FROM UrgencyAttention u, Patient p WHERE p.document=u.patient.document WHERE u.date BETWEEN ?1 AND ?2 GROUPBY p.document HAVING COUNT(u)>?3")
//	public List<Patient> findBetweenDatesByAmount(LocalDate date1, LocalDate date2, Long amount);
}


