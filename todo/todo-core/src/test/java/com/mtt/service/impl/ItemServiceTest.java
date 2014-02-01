package com.mtt.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.mtt.core.model.Item;
import com.mtt.core.model.User;
import com.mtt.core.repository.ItemRepository;
import com.mtt.core.repository.UserRepository;
import com.mtt.core.service.ItemService;
import com.mtt.core.service.exception.ItemNotFoundException;
import com.mtt.core.service.impl.ItemServiceImpl;


public class ItemServiceTest {
	
	@InjectMocks
	private ItemService itemService = new ItemServiceImpl();
	@Mock
	private ItemRepository itemRepository;
	@Mock
	private UserRepository userRepository;
	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	
	 @Test
	 public void findAll() {
	        List<Item> items = new ArrayList<Item>();
	        when(itemRepository.findAll()).thenReturn(items);
	        
	        List<Item> returned = itemService.findAll();
	        
	        verify(itemRepository, times(1)).findAll();
	        verifyNoMoreInteractions(itemRepository);
	        
	        assertEquals(items, returned);
	 }
	
	 @Test
	    public void findById() {
		 	final Long ITEM_ID = new Long(1);
	        Item item = new Item(new Long(ITEM_ID),"My Description to delete",new User("ken", "password1", 1) );
		 
	        when(itemRepository.findOne(ITEM_ID)).thenReturn(item);
	        
	        Item returned = itemService.find(ITEM_ID);
	        
	        verify(itemRepository, times(1)).findOne(ITEM_ID);
	        verifyNoMoreInteractions(itemRepository);
	        
	        assertEquals(item, returned);
	    }
	 
	 

	@Test
	public void testCreateItem() {
		Item newItem = new Item();
		newItem.setDescription("My First Item");
		
        when(itemRepository.save(any(Item.class))).thenReturn(newItem);
		Item createdItem = itemService.create(newItem, new User("ken", "password1", 1));
        
        assertNotNull(createdItem);
        assertEquals(newItem.getDescription(),createdItem.getDescription());
        assertEquals(newItem.getUser().getUsername(),createdItem.getUser().getUsername());
	}
	
	
	    @Test
	    public void delete() throws ItemNotFoundException {
	    	final Long ITEM_ID = new Long(1);
	        Item deleted = new Item(new Long(ITEM_ID),"My Description to delete",new User("ken", "password1", 1) );
	        when(itemRepository.findOne(ITEM_ID)).thenReturn(deleted);
	        
	        Item returned = itemService.delete(ITEM_ID);
	        
	        verify(itemRepository, times(1)).findOne(ITEM_ID);
	        verify(itemRepository, times(1)).delete(deleted);
	        verifyNoMoreInteractions(itemRepository);
	        
	        assertEquals(deleted, returned);
	    }
	
	
	  @Test(expected = ItemNotFoundException.class)
	    public void deleteWhenItemIsNotFound() throws ItemNotFoundException {
		    final Long ITEM_ID = new Long(1);
	        when(itemRepository.findOne(any(Long.class))).thenReturn(null);
	        
	        itemService.delete(ITEM_ID);
	        
	        verify(itemRepository, times(1)).findOne(ITEM_ID);
	        verifyNoMoreInteractions(itemRepository);
	    }
	  
	  
	  @Test
	    public void update() throws ItemNotFoundException {
		   final Long ITEM_ID = new Long(1);
	       Item item = new Item(new Long(ITEM_ID),"My Description to delete",new User("ken", "password1", 1) );
	       when(itemRepository.findOne(ITEM_ID)).thenReturn(item);
	        
	       Item returned = itemService.updateItemDescription("new description", ITEM_ID);
	        
	        verify(itemRepository, times(1)).findOne(ITEM_ID);
	        verifyNoMoreInteractions(itemRepository);
	        
	        assertEquals("new description",returned.getDescription());
	        assertEquals(ITEM_ID,returned.getId());
	    }
	  
	  
	  @Test(expected = ItemNotFoundException.class)
	    public void updateWhenItemIsNotFound() throws ItemNotFoundException {
		    final Long ITEM_ID = new Long(1);
	        when(itemRepository.findOne(any(Long.class))).thenReturn(null);
	        
	        itemService.updateItemDescription("new description", ITEM_ID);
	        
	        verify(itemRepository, times(1)).findOne(ITEM_ID);
	        verifyNoMoreInteractions(itemRepository);
	    }

	

}
