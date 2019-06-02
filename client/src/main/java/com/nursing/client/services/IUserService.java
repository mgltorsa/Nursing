package com.nursing.client.services;

import com.nursing.client.model.User;

/**
 * IUserService
 */
public interface IUserService {

    public User addUser(User user);
	
	public User removeAttention(String login);
	
    public User updateUser(User user);
    
    public User getUser(String login);

    public boolean validateUser(User user);

    
}