package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

/**
 * @author sterotto
 * @describe 用户分组的成员
 */
@Entity
@Table(name="group_friend")
public class GroupFriend implements Serializable
{
	private static final long serialVersionUID = 1L;
	public static final int INVITE = 1;
	public static final int INVITED = 2;
	public static final int FRIEND = 3;
	public static final int STRANGER = 0;
	public static final int REFUSE = -1;
	public static final int BLACKLIST = -2;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@Expose
	private Integer id;
	
	@ManyToOne(cascade=CascadeType.REFRESH)        
	@JoinColumn(name="user_group_id") 
	@Expose(serialize=false)
	private UserGroup userGroup;
	
	@Column(name="user_id",nullable = false)
	@Expose
	private Integer userId;
	
	@Column(name="remark")
	@Expose
	private String remark;
	
	@Column(name="status",nullable = false)
	@Expose
	private Integer status = 0;
	
	@Column(name="create_time")
	@Expose
	private String createTime;
	@Transient
	@Expose
	private String accnumno;
	@Transient
	@Expose
	private String username;
	@Transient
	@Expose
	private String nickname;
	@Transient
	@Expose
	private String img;
	
	public UserGroup getUserGroup()
	{
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup)
	{
		this.userGroup = userGroup;
	}
	public Integer getUserId()
	{
		return userId;
	}
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getAccnumno() {
		return accnumno;
	}
	public void setAccnumno(String accnumno) {
		this.accnumno = accnumno;
	}
	@Override
	public String toString() {
		return "GroupFriend [id=" + id + ", userGroup=" + userGroup + ", userId=" + userId + ", username=" + username
				+ ", nickname=" + nickname + ", img=" + img + ", createTime=" + createTime + "]";
	}
	
	
}
