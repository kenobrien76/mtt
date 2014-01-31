package com.mtt.core.service;

import java.util.List;

import com.mtt.core.model.Item;
import com.mtt.core.service.exception.ItemNotFoundException;

public interface ItemService {
	
	/**
	 * Create an item
	 * @param item
	 * @return
	 */
	Item create(final Item item, final String userId);
	
	
	/**
	 * Update an item's description
	 * @param item
	 * @return
	 * @throws ItemNotFoundException 
	 */
	Item updateItemDescription(final String description, final Long itemId) throws ItemNotFoundException;
	
	/**
	 * 
	 * @return
	 */
	List<Item> findAll();
	
	
	/**
	 * 
	 * @return
	 */
	List<Item> findAllForUser(final String userId);
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Item findById(final Long id) throws ItemNotFoundException;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Item deleteById(final Long id)  throws ItemNotFoundException;

}
