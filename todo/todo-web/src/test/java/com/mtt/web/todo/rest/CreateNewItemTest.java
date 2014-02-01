package com.mtt.web.todo.rest;

import static com.mtt.web.todo.utils.TestUtils.convertObjectToJsonBytes;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Ignore;
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
import com.mtt.todo.web.dto.ItemDTO;

public class CreateNewItemTest {
	
	
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
	public void thatCreateItemHttpCreated() throws Exception {

	ItemDTO dto = new ItemDTO();
	dto.setDescription("My new Item");
	when(itemService.create(any(Item.class),any(User.class))).thenReturn(createItem("test item", new User("ken", "pass",1)));
		this.mockMvc.perform(
	            post("/items")
	                    .content(convertObjectToJsonBytes(dto))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated());
	  }



	private Item createItem(String description, final User user) {
       Item item = new Item();
       item.setDescription(description);
       item.setUser(user);
		return item;
	}


}
