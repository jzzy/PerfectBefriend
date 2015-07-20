package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="groupmembers")
public class GroupMembers implements Serializable{
	/**
	 * 群成员  关系表
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="userid")//用户id
	private int userid;
	@Column(name="groupid")//群id
	private int groupid;
	@Column(name="urp")//用户 与群的关系  0等待审核  1 是群成员 2 被群主 请出的用户
	private int urp;
	@Column(name="time")//时间
	private String time;
	
	 
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public int getUrp() {
		return urp;
	}
	public void setUrp(int urp) {
		this.urp = urp;
	}
	

}
