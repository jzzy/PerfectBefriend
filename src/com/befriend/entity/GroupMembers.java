package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "groupmembers")
public class GroupMembers implements Serializable {
	/**
	 * 群成员 关系表
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Column(name = "userid")
	// 用户id
	@Expose
	private int userid;
	@Column(name = "groupid")
	// 群id
	@Expose
	private int groupid;
	@Column(name = "urp")
	// 用户 与群的关系 0等待审核 1 是群成员 2 被群主 请出的用户 3是群主
	@Expose
	private int urp;
	@Column(name = "time")
	// 时间
	@Expose
	private String time;

	@Column(name = "message")
	// 验证消息
	@Expose
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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
