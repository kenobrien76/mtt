package com.mtt.web.todo.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import com.mtt.core.model.Item;
import com.mtt.core.model.User;
import com.mtt.core.service.ItemService;
import com.mtt.core.service.UserService;
import com.mtt.core.service.exception.UserNotFoundException;

public class ViewItemTest {
	
	
	MockMvc mockMvc;
	@InjectMocks
	ItemController itemCommandController  = new ItemController();
	@Mock
	ItemService itemService;
	@Mock
	UserService userService;
	
	
	

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(itemCommandController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}
	
	
	@Test
	public void thatReadItemsNoUserHttpNotFound() throws Exception {
		
		 when(userService.findUser(any(String.class))).thenThrow(new UserNotFoundException());

		    this.mockMvc.perform(
		            get("/items")
	                .accept(MediaType.APPLICATION_JSON))
	        .andDo(print())
	        .andExpect(status().isNotFound());
		
	}
	
	
	
	@Test
	public void thatReadItemsHttpOK() throws Exception {
	    when(itemService.findAllForUser(any(User.class))).thenReturn(createItems("My test item", 
	    					"My test item2", new User("ken", "pass",1)));
	    this.mockMvc.perform(
	            get("/items")
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
	  }
	
	
	@Test
	  public void thatReadItemsRendersCorrectly() throws Exception {

	    when(itemService.findAllForUser(any(User.class))).thenReturn(createItems("My test item 1", 
				"My test item 2", new User("ken", "pass",1)));
	   
	    mockMvc.perform(get("/items"))
        	.andExpect(status().isOk())
        	.andExpect(jsonPath("$", hasSize(2)))
        	.andExpect(jsonPath("$[0].description", is("My test item 1")))
        	.andExpect(jsonPath("$[0].userId", is("ken")))
        	.andExpect(jsonPath("$[1].description", is("My test item 2")))
        	.andExpect(jsonPath("$[1].userId", is("ken")));

	  }


	private List<Item> createItems(final String descriptionOne, final String descriptionTwo, final User user) {
		List<Item> items = new ArrayList<Item>();
		items.add(createItem(descriptionOne, user));
		items.add(createItem(descriptionTwo, user));
		return items;
	}
	
	


	

	private Item createItem(String description, final User user) {
       Item item = new Item();
       item.setDescription(description);
       item.setUser(user);
		return item;
	}


}
