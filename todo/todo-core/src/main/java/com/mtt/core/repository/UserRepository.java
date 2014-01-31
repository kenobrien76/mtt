package com.mtt.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtt.core.model.User;



public interface UserRepository extends JpaRepository<User, String> {
	

}
