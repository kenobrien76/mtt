package com.mtt.web.todo.rest;

import static com.mtt.todo.web.dto.ItemDTO.fromItem;
import static com.mtt.todo.web.dto.ItemDTO.fromItems;
import static com.mtt.todo.web.dto.ItemDTO.toItem;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtt.core.service.ItemService;
import com.mtt.core.service.UserService;
import com.mtt.todo.web.dto.ItemDTO;

@Controller
public class ItecUserController {
	
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private UserService userService;
	
	
	

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	@Transactional(value="transactionManager",rollbackFor=Throwable.class, readOnly=true)
	public @ResponseBody List<ItemDTO> read(HttpServletRequest request, 
	        HttpServletResponse response) throws Exception {
		return fromItems(
				 itemService.findAllForUser(
						 (String)request.getSession().getAttribute("username")),
						 new ArrayList<ItemDTO>());
	}
	
	@RequestMapping(value = "/items", method = RequestMethod.POST)
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public @ResponseBody ItemDTO add(HttpServletRequest request, 
	        HttpServletResponse response,@RequestBody ItemDTO itemDTO) throws Exception {
		return fromItem(itemService.create(toItem(itemDTO), 
				 (String)request.getSession().getAttribute("username")));
	}
	
	
	@RequestMapping(value = "/items/{itemId}", method = RequestMethod.PUT)
	public @ResponseBody ItemDTO update(HttpServletRequest request, 
	        HttpServletResponse response,@RequestBody ItemDTO itemDTO,@PathVariable String itemId) throws Exception {
		return fromItem(itemService.updateItemDescription(itemDTO.getDescription(),Long.valueOf(itemId)));
	}
	
	
	
	
	

	@RequestMapping(value = "/items/{itemId}", method = RequestMethod.DELETE)
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public @ResponseBody String deleteItem(@PathVariable String itemId) throws Exception {
		itemService.deleteById(Long.valueOf(itemId));
		return "{\"success\":true}";
	}
	
	
}
