package com.ssm.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ssm.common.pojo.UserTO;

/**
 * 用户实体类
 * 
 * @author kitty
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

	private static final long serialVersionUID = 4959095313516358505L;

	@NotNull
	@Min(1L)
	private Long id;

	@XmlElement(name = "username")
	@NotNull
	@Size(min = 6, max = 200)
	private String username;

	@NotNull
	@Size(min = 6, max = 20)
	private String password;

	@NotNull
	private Date createTime;

	@NotNull
	private Date updateTime;

	public User() {
	}

	public User(UserTO userTO) {
		this.id = userTO.getId();
		this.username = userTO.getUsername();
		this.password = userTO.getPassword();
	}

	public User(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
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
