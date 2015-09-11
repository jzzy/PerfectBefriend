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
	public static final String MY_FRIEND = "好友";
	public static final String MY_BLACKLIST = "黑名单";
	public static final int FRIEND_DEFAULT = 1;
	public static final int BLACKLIST_DEFAULT = 2;
	public static final int NOT_DEFAULT = 0;
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@Expose
	private Integer id;
	
	@ManyToOne(cascade=CascadeType.REFRESH)        
	@JoinColumn(name="user_id") //这是数据库里面的，这个字段是关联外键
	@Expose(serialize=false)
	private User user;//与这里对应，这里表示一
	
	@Column(name="name",nullable=false)
	@Expose
	private String name;
	
	@Column(name="is_default",nullable = false)
	@Expose
	private int isDefault = 0;
	
	@Column(name="order_num",nullable=false)
	@Expose
	private int orderNum; 
	
	@Column(name="create_time")
	@Expose
	private String createTime;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST,mappedBy="userGroup")
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
	
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	@Override
	public String toString() {
		return "UserGroup [id=" + id + ", user=" + user  + ", name=" + name
				+ ", orderNum=" + orderNum + ", createTime=" + createTime + "]";
	}
	
}
