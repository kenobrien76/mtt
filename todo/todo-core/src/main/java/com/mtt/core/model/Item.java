package com.mtt.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;



@Entity
@Table(name = "items")
public class Item implements Serializable {
	
	private static final long serialVersionUID = 4658192093790613543L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Column(name="description", nullable=false)
	private String description;
	@Column(name="creation_time", nullable=false)
	private Date creationTime;
	@Column(name = "modification_time", nullable = false)
    private Date modificationTime;
	@ManyToOne
	@JoinColumn(name=  "username", nullable = false)
	private User user;
	
	
	public Item() {}
	
	
	
	
	public Item(Long id, String description, User user) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
	}




	public String getDescription() {
		return description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	
	public Date getCreationTime() {
		return creationTime;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Date getModificationTime() {
		return modificationTime;
	}


	public Long getId() {
		return id;
	}
	
	 @PreUpdate
	 public void preUpdate() {
	        modificationTime = new Date();
	 }
	   
	 @PrePersist
	 public void prePersist() {
	        Date now = new Date();
	        creationTime = now;
	        modificationTime = now;
	 }



	@Override
	public String toString() {
		return "Item [id=" + id + ", description=" + description
				+ ", creationTime=" + creationTime + ""
				 + ", username=" + user.getUsername() + ""
						+ "	, modificationTime="+modificationTime+"]";
	}
	
	
	
	
	
	
	
	
	

}
