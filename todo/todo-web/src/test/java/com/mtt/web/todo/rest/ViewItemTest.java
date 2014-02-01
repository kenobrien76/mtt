package com.mtt.web.todo.rest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static  com.mtt.web.todo.utils.TestUtils.convertObjectToJsonBytes;

import com.mtt.core.model.Item;
import com.mtt.core.model.User;
import com.mtt.core.service.ItemService;
import com.mtt.todo.web.dto.ItemDTO;

public class ViewItemTest {
	
	
	MockMvc mockMvc;
	@InjectMocks
	ItemController itemCommandController  = new ItemController();
	@Mock
	ItemService itemService;
	
	
	

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(itemCommandController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}
	
	
	@Test
	@Ignore
	public void thatViewItemHttpNotFound() throws Exception {
	}
	
	


	private Item createItem(String description, final User user) {
       Item item = new Item();
       item.setDescription(description);
       item.setUser(user);
		return item;
	}


}
