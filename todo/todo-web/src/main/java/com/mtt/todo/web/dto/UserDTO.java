package com.mtt.todo.web.dto;

import com.mtt.core.model.User;


public class UserDTO {

	private String username;

	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static UserDTO fromUser(final User user){
		UserDTO dto = new UserDTO();
		dto.setUsername(user.getUsername());
		return dto;
	}

	
	
	
}
