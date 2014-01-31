package com.mtt.service.impl;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mtt.core.model.Item;
import com.mtt.core.service.ItemService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"todo-core-context.xml"})
public class ItemServiceTest {
	
	@Autowired
	private ItemService itemService;

	@Test
	@Ignore
	public void testAddNewItem() {
		Item item = new Item();
		item.setDescription("my first item");
		//item = itemService.create(item);
		assertNotNull(item);	
	}

}
