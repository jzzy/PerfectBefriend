package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cis")
/**
 * 群聊天信息表
 */
public class Cis implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "senduserid")
	/**
	 * 发信息的用户id
	 */
	private int senduserid;
	@Column(name = "groupid")
	/**
	 * 群id
	 */
	private int groupid;
	@Column(name = "information")
	/**
	 * 发送的 消息
	 */
	private String information;
	@Column(name = "time")
	/**
	 * 发送的 时间
	 */
	private String time;
	@Column(name = "userid")
	/**
	 * 我的用户id 如果我当时 没接受到 就会把我的  用户id  存起来 我收到以后 会删除掉
	 */
	private int userid;
	@Column(name = "online")
	/**
	 * 0未读 1已读
	 */
	private Integer online;
	@Column(name = "ip")
	/**
	 * 客户端ip
	 */
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSenduserid() {
		return senduserid;
	}

	public void setSenduserid(int senduserid) {
		this.senduserid = senduserid;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
