package com.mtt.todo.web.dto;


import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.mtt.core.model.Item;
import com.mtt.todo.web.json.JsonDateSerializer;


public class ItemDTO {

	private String description;
	private String userId;
	private Long id;
	private Date creationTime;
	
	
	

	public ItemDTO() {}

	public ItemDTO(final Long id, final String description, final String userId, final Date creationTime) {
		this.id = id;
		this.description = description;
		this.userId = userId;
		this.creationTime = creationTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	
	public static Item toItem(final ItemDTO itemDTO){
		Item item = new Item();
		item.setDescription(itemDTO.getDescription());
		return item;
	}
	
	public static ItemDTO fromItem(final Item item){
		return new ItemDTO(item.getId(),item.getDescription(),
				item.getUser().getUsername(),item.getCreationTime());
	}
	
	
	public static List<ItemDTO> fromItems(final List<Item> items, final List<ItemDTO> itemDTOs){
		for(Item item:items){
			itemDTOs.add(new ItemDTO(item.getId(),
								item.getDescription(), 
										item.getUser().getUsername(),
													item.getCreationTime()));
		}
		return itemDTOs;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCreationTime() {
		return creationTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	

	
	
	
}
