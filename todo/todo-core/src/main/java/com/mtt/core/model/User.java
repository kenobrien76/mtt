package com.mtt.core.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="USERS")
public class User implements Serializable{

	@Id
	@Column(name="username", unique=true, nullable=false, length=10)
	private String username;
	
	@Column(name="password", nullable = false, length=32)
	private String password;
	
	@Column(name="enabled", nullable = false)
	private int enabled;

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="AUTHORITIES", joinColumns=@JoinColumn(name="username"))
	@Column(name="authority")
	private List<String> authority;
	@OneToMany(mappedBy="user")
    public Set<Item> items;
	
	
	public List<String> getAuthority() {
		return authority;
	}

	public void setAuthority(List<String> authority) {
		this.authority = authority;
	}

	public User(){
		
	}
	
	public User(String username,String password,int enabled){
		this.setUsername(username);
		this.setPassword(password);
		this.setEnabled(enabled);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Set<Item> getItems() {
		return items;
	}

	
	
}
