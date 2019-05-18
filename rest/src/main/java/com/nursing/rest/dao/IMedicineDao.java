package com.nursing.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nursing.rest.model.Medicine;

@Repository
public interface IMedicineDao extends JpaRepository<Medicine, Long> {


	List<Medicine> findByContraIndications(String contraIndications);
}
