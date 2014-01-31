package com.mtt.core.service;

import com.mtt.core.model.User;
import com.mtt.core.service.exception.ItemNotFoundException;

public interface UserService {
	
	/**
	 * Create a user
	 * @param user
	 * @return
	 */
	User create(final User user);
	
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	User findUser(final String username);
	
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	User deleteByUsername(final String username)  throws ItemNotFoundException;

}
