package com.mtt.web.todo.rest;

import static com.mtt.todo.web.dto.UserDTO.fromUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mtt.core.service.UserService;
import com.mtt.core.service.exception.UserNotFoundException;
import com.mtt.todo.web.dto.UserDTO;


@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/users/current", method = RequestMethod.GET)
	@Transactional(value="transactionManager",rollbackFor=Throwable.class, readOnly=true)
	public ResponseEntity<UserDTO> getUser(HttpServletRequest request, 
	        HttpServletResponse response){
        try {
        	return new ResponseEntity<UserDTO>(
        			fromUser(userService.findUser((String)request.getSession().getAttribute("username"))),
        					HttpStatus.OK);
			
		} catch (UserNotFoundException e) {
			//add logging
		}
        
        return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);

	}


}
