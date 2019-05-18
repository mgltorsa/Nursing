package com.nursing.rest.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nursing.rest.model.Supply;

@Repository
public interface ISupplyDao extends JpaRepository<Supply, Long> {

		
}
