package com.nursing.rest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nursing.rest.model.InventoryMedicine;
import com.nursing.rest.model.Supply;
import com.nursing.rest.model.UrgencyAttention;

@Repository
public interface ISupplyDao extends JpaRepository<Supply, Long> {

	@Query("SELECT s FROM Supply s WHERE s.medicine.consecutive=?1")
	List<Supply> getMedicineSupplies(Long id);
	
	@Query("SELECT s FROM Supply s WHERE s.medicine.consecutive=?2")
	public List<Supply> findByMedicineName(String name);
	
	@Query("SELECT s FROM Supply s WHERE s.quantity BETWEEN ?1 AND ?2")
	public List<Supply> findByQuantityRange(int minimum, int maximum);
	
	@Query("SELECT s FROM Supply s WHERE s.patient.names=?1")
	public List<Supply> findByPatientName(String patientName);

	@Query("SELECT s FROM Supply s WHERE s.patient.document=?1")
	public List<Supply> findByPatient(String patientDocument);

	@Query("SELECT s FROM Supply s WHERE s.urgencyAttention.consecutive=?1")
	public List<Supply> findByUrgency(Long urgencyID);
	
	@Query("SELECT m FROM Supply m WHERE m.quantity<?1")
	public List<Supply> findByQuantityLessThan(Long quantity);
}
