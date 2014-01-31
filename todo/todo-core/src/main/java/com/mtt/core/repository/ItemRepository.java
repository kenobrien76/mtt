package com.mtt.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtt.core.model.Item;
import com.mtt.core.model.User;


public interface ItemRepository extends JpaRepository<Item, Long> {
	
	
	public List<Item> findByUser(final User user);
	

}
