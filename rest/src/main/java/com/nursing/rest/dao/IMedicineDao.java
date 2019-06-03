package com.nursing.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nursing.rest.model.InventoryMedicine;
import com.nursing.rest.model.Medicine;
import com.nursing.rest.model.Supply;

@Repository
public interface IMedicineDao extends JpaRepository<Medicine, Long> {
	
	List<Medicine> findByContraIndications(String contraIndications);

	Medicine findByConsecutive(Long consecutive);
	
	List<Medicine> findByName(String name);
	
	List<Medicine> findByGenericName(String geneticName);
	
	List<Medicine> findByLaboratory(String laboratory);
	
	List<Medicine> findByAdministrationType(String administrationType);
	
}
