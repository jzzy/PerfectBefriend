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
@Table(name = "cis")
/**
 * Ⱥ������Ϣ��
 */
public class Cis implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Column(name = "senduserid")
	@Expose
	private int senduserid;
	@Column(name = "groupid")
	@Expose
	private int groupid;
	@Column(name = "information")
	@Expose
	private String information;
	@Column(name = "time")
	@Expose
	private String time;
	@Column(name = "userid")
	@Expose
	private int userid;
	@Column(name = "online")
	@Expose
	private Integer online;
	@Column(name = "ip")
	@Expose
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
