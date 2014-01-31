package com.mtt.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtt.core.repository.ItemRepository;
import com.mtt.core.repository.UserRepository;
import com.mtt.core.service.ItemService;
import com.mtt.core.service.exception.ItemNotFoundException;
import com.mtt.core.model.Item;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
	
	@Resource
	private ItemRepository itemRepository;
	@Resource
	private UserRepository userRepository;



	@Override
	public Item create(final Item item, final String userId) {
		item.setUser(userRepository.findOne(userId));
		return itemRepository.save(item);
	}

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public Item findById(final Long id) throws ItemNotFoundException {
		return itemRepository.findOne(id);
	}

	@Override
	public Item deleteById(Long id) throws ItemNotFoundException {
		final Item item = itemRepository.findOne(id);
		itemRepository.delete(id);
		return item;
	}

	@Override
	public List<Item> findAllForUser(final String userId) {
		return itemRepository.findByUser(userRepository.findOne(userId));
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
