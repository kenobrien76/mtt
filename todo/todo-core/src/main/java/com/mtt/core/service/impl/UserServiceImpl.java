package com.mtt.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtt.core.model.User;
import com.mtt.core.repository.UserRepository;
import com.mtt.core.service.UserService;
import com.mtt.core.service.exception.ItemNotFoundException;
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User create(final User user) {
		return userRepository.save(user);
	}

	@Override
	public User findUser(final String username) {
		return userRepository.findOne(username);
	}


	@Override
	public User deleteByUsername(final String username) throws ItemNotFoundException {
		final User userToDelete = userRepository.findOne(username);
		userRepository.delete(userToDelete);
		return userToDelete;
	}

}
