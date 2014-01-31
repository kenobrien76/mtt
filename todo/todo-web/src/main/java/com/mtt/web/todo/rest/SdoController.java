package com.mtt.web.todo.rest;

import static com.mtt.todo.web.dto.UserDTO.fromUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtt.core.service.UserService;
import com.mtt.todo.web.dto.UserDTO;

@Controller
public class SdoController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/users/current", method = RequestMethod.GET)
	@Transactional(value="transactionManager",rollbackFor=Throwable.class, readOnly=true)
	public @ResponseBody UserDTO getUser(HttpServletRequest request, 
	        HttpServletResponse response){
        return fromUser(userService.findUser((String)request.getSession().getAttribute("username")));

	}


}
