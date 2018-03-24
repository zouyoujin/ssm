package com.ssm.common.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 用户实体类
 * 
 * @author kitty
 */
@TableName("tbl_users")
public class User extends BaseModel {

	private static final long serialVersionUID = 4959095313516358505L;

	/**
	 * 登陆帐户
	 */
	@TableField("account")
	@NotNull
	@Size(min = 6, max = 20)
	private String account;
	/**
	 * 密码
	 */
	@TableField("password")
	@NotNull
	@Size(min = 6, max = 50)
	private String password;
	/**
	 * 用户类型(1普通用户2管理员3系统用户)
	 */
	@TableField("user_type")
	private String userType;
	/**
	 * 姓名
	 */
	@TableField("user_name")
	private String userName;
	/**
	 * 姓名拼音
	 */
	@TableField("name_pinyin")
	private String namePinyin;
	/**
	 * 性别(0:未知;1:男;2:女)
	 */
	@TableField("sex")
	private Integer sex;
	/**
	 * 头像
	 */
	@TableField("avatar")
	private String avatar;
	/**
	 * 电话
	 */
	@TableField("phone")
	@NotNull
	@Size(min = 0, max = 50)
	private String phone;
	/**
	 * 邮箱
	 */
	@TableField("email")
	@NotNull
	@Size(min = 0, max = 64)
	private String email;
	/**
	 * 身份证号码
	 */
	@TableField("id_card")
	private String idCard;
	/**
	 * 微信
	 */
	@TableField("wei_xin")
	private String weiXin;
	/**
	 * 微博
	 */
	@TableField("wei_bo")
	private String weiBo;
	/**
	 * QQ
	 */
	@TableField("qq")
	private String qq;
	/**
	 * 出生日期
	 */
	@TableField("birth_day")
	private Date birthDay;
	/**
	 * 部门编号
	 */
	@TableField("dept_id")
	private Long deptId;
	/**
	 * 职位
	 */
	@TableField("position")
	private String position;
	/**
	 * 详细地址
	 */
	@TableField("address")
	private String address;
	/**
	 * 工号
	 */
	@TableField("staff_no")
	private String staffNo;

	@TableField(exist = false)
	private String oldPassword;
	@TableField(exist = false)
	private String deptName;
	@TableField(exist = false)
	private String userTypeText;
	@TableField(exist = false)
	private String permission;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNamePinyin() {
		return namePinyin;
	}

	public void setNamePinyin(String namePinyin) {
		this.namePinyin = namePinyin;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getWeiXin() {
		return weiXin;
	}

	public void setWeiXin(String weiXin) {
		this.weiXin = weiXin;
	}

	public String getWeiBo() {
		return weiBo;
	}

	public void setWeiBo(String weiBo) {
		this.weiBo = weiBo;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserTypeText() {
		return userTypeText;
	}

	public void setUserTypeText(String userTypeText) {
		this.userTypeText = userTypeText;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
