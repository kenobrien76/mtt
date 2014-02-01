package com.mtt.core.service;

import java.util.List;

import com.mtt.core.model.Item;
import com.mtt.core.model.User;
import com.mtt.core.service.exception.ItemNotFoundException;
import com.mtt.core.service.exception.UserNotFoundException;

public interface ItemService {
	
	/**
	 * Create an item
	 * @param item
	 * @return
	 */
	Item create(final Item item, final User user);
	
	
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
	List<Item> findAllForUser(final User user);
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Item find(final Long id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Item delete(final Long id)  throws ItemNotFoundException;

}
