package com.nursing.client.repositories;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nursing.client.model.User;

/**
 * IUserRepository
 */
@Repository
public interface IUserRepository extends CrudRepository<User,String>{

    
   
    
}