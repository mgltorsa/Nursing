package com.nursing.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nursing.rest.model.UrgencyAttention;

@Repository
public interface IUrgencyAttentionDao extends JpaRepository<UrgencyAttention, Long>{

}
