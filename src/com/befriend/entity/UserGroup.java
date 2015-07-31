package com.befriend.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * @author sterotto
 * @describe 用户好友分组信息
 */
@Entity
@Table(name="user_group")
public class UserGroup implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@Expose
	private Integer id;
	
	@ManyToOne(cascade=CascadeType.ALL)        
	@JoinColumn(name="user_id") 
	@Expose(serialize=false)
	private User user;
	
	@Column(name="name",nullable=false)
	@Expose
	private String name;
	
	@Column(name="order_num",nullable=false)
	@Expose
	private int orderNum;
	
	@Column(name="create_time")
	@Expose
	private String createTime;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="userGroup")
	@Expose
	private Set<GroupFriend> groupFriends = new HashSet<GroupFriend>();
	
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public Set<GroupFriend> getGroupFriends()
	{
		return groupFriends;
	}
	public void setGroupFriends(Set<GroupFriend> groupFriends)
	{
		this.groupFriends = groupFriends;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getOrderNum()
	{
		return orderNum;
	}
	public void setOrderNum(int orderNum)
	{
		this.orderNum = orderNum;
	}
	public String getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "UserGroup [id=" + id + ", user=" + user + ", groupFriends=" + groupFriends + ", name=" + name
				+ ", orderNum=" + orderNum + ", createTime=" + createTime + "]";
	}
	
}
