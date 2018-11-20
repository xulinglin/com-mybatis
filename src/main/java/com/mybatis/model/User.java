package com.mybatis.model;

import java.util.Date;

public class User {
	
	public User(){
		
	}
	
	public User(Integer id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	Integer id;
	String username;
	String password;
	String state;
	Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", state=" + state
				+ ", createTime=" + createTime + "]";
	}
	
	
}
