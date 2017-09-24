package com.ssm.common.pojo;

import java.io.Serializable;

/**
 * 
 * 用户实体TO
 * 
 * @author Kitty
 *
 */
public class UserTO implements Serializable {

	private static final long serialVersionUID = 7844979436050883433L;

	// 用户Id
	private Long id;

	// 用户名称
	private String username;

	// 密码
	private String password;
	
	//用户电话号码
	private String phone;
	
	//用户邮箱
	private String email;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
