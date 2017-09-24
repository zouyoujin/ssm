package com.ssm.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ssm.common.pojo.UserTO;

/**
 * 用户实体类
 * 
 * @author kitty
 */
public class User implements Serializable {

	private static final long serialVersionUID = 4959095313516358505L;

	/**
	 * 用户ID
	 */
	@NotNull
	@Min(1L)
	private Long id;

	/**
	 * 用户名称
	 */
	@NotNull
	@Size(min = 6, max = 50)
	private String username;

	/**
	 * 用户密码(加密)
	 */
	@NotNull
	@Size(min = 6, max = 32)
	private String password;

	/**
	 * 用户电话号码
	 */
	@NotNull
	@Size(min = 0, max = 20)
	private String phone;

	/**
	 * 用户邮箱
	 */
	@NotNull
	@Size(min = 0, max = 50)
	private String email;

	/**
	 * 用户创建时间
	 */
	@NotNull
	private Date createTime;

	/**
	 * 用户更新时间
	 */
	@NotNull
	private Date updateTime;

	public User() {
	}

	public User(UserTO userTO) {
		if (userTO != null) {
			this.id = userTO.getId();
			this.username = userTO.getUsername();
			this.password = userTO.getPassword();
			this.phone = userTO.getPhone();
			this.email = userTO.getEmail();
		}
	}

	public User(Long id, String username, String password, String phone, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.email = email;
	}

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

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
