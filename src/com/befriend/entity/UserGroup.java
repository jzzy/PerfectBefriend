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
import javax.persistence.Transient;

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
	private Integer id;
	
	@ManyToOne(cascade=CascadeType.ALL)        
	@JoinColumn(name="user_id")  
	private User user;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="userGroup")
	private Set<GroupFriend> groupFriends = new HashSet<GroupFriend>();
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="order_num",nullable=false)
	private int orderNum;
	
	@Column(name="create_time")
	private String createTime;
	
	@Transient
	private Set<User> friends = new HashSet<User>();
	
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
	public void setFriends(Set<User> friends)
	{
		this.friends = friends;
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

}
