package com.mtt.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtt.core.model.Item;
import com.mtt.core.model.User;
import com.mtt.core.repository.ItemRepository;
import com.mtt.core.repository.UserRepository;
import com.mtt.core.service.ItemService;
import com.mtt.core.service.exception.ItemNotFoundException;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
	
	@Resource
	private ItemRepository itemRepository;
	@Resource
	private UserRepository userRepository;



	@Override
	public Item create(final Item item, final User user) {
		item.setUser(user);
		return itemRepository.save(item);
	}

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public Item find(final Long itemId){
		return itemRepository.findOne(itemId);
	}

	@Transactional(rollbackFor = ItemNotFoundException.class)
	@Override
	public Item delete(Long itemId) throws ItemNotFoundException {
		final Item deleted = itemRepository.findOne(itemId);
		if(deleted == null){
			throw new ItemNotFoundException();
		}
		itemRepository.delete(deleted);
		return deleted;
	}
	
	

	@Override
	public List<Item> findAllForUser(final User user) {
		return itemRepository.findByUser(user);
	}

	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	@Override
	public Item updateItemDescription(final String description, final Long itemId) throws ItemNotFoundException  {
		Item item = itemRepository.findOne(itemId);
		if(item == null){
			throw new ItemNotFoundException();
		}
		item.setDescription(description);
		return item;
	}
	

}
