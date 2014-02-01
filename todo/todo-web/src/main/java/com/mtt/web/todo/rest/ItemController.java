package com.mtt.web.todo.rest;

import static com.mtt.todo.web.dto.ItemDTO.fromItem;
import static com.mtt.todo.web.dto.ItemDTO.fromItems;
import static com.mtt.todo.web.dto.ItemDTO.toItem;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtt.core.service.ItemService;
import com.mtt.core.service.UserService;
import com.mtt.core.service.exception.ItemNotFoundException;
import com.mtt.core.service.exception.UserNotFoundException;
import com.mtt.todo.web.dto.ItemDTO;

@Controller
@RequestMapping("/items")
public class ItemController {
	
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(method = RequestMethod.POST)
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public ResponseEntity<ItemDTO> add(HttpSession session,@RequestBody ItemDTO itemDto){
		try {
			return new ResponseEntity<ItemDTO>(fromItem(itemService.create(toItem(itemDto),
										userService.findUser((String)session.getAttribute("username")))),HttpStatus.CREATED);
		} catch (UserNotFoundException e) {
		}
		return new ResponseEntity<ItemDTO>(HttpStatus.NOT_FOUND);
	}
	

	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{itemId}")
	public ResponseEntity<ItemDTO>  updateDescription(
				@RequestBody ItemDTO itemDTO,@PathVariable String itemId){

		try{
			
			return new ResponseEntity<ItemDTO>(
								fromItem(itemService.updateItemDescription(
												itemDTO.getDescription(),Long.valueOf(itemId))),HttpStatus.OK);
		}catch(ItemNotFoundException infe){
			 //logging
		}
		return new ResponseEntity<ItemDTO>(HttpStatus.NOT_MODIFIED);

	}
	
	
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{itemId}")
	public ResponseEntity<ItemDTO>  removeItem(@PathVariable String itemId){

		try{
			return new ResponseEntity<ItemDTO>(fromItem(
						itemService.delete(Long.valueOf(itemId))),HttpStatus.OK);
		}catch(ItemNotFoundException infe){
			 //logging
		}
		return new ResponseEntity<ItemDTO>(HttpStatus.NOT_FOUND);

	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	@Transactional(value="transactionManager",rollbackFor=Throwable.class, readOnly=true)
	public ResponseEntity<List<ItemDTO>> read(HttpSession session) {
		try {
			return new ResponseEntity<List<ItemDTO>>(
					fromItems( 
								itemService.findAllForUser(
											userService.findUser((String)session.getAttribute("username"))),
																	new ArrayList<ItemDTO>()),
																					HttpStatus.OK
																												);
					
		} catch (UserNotFoundException e) {
			//logging
		}	
		
		return new ResponseEntity<List<ItemDTO>>(HttpStatus.NOT_FOUND);
	}
	

	

	
	
}
