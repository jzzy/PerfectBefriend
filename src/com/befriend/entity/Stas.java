package com.befriend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stas")
public class Stas {
	/**
	 * 每天记录  统计
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="vored")//每天新增游客数量
	private int vored;
	@Column(name="usersaved")//每天新增用户数量
	private int usersaved;
	@Column(name="userlogined")//每天登入用户数量
	private int userlogined;
	@Column(name="usersyned")//每天同时在线(巅峰)用户数量
	private int usersyned;
	@Column(name="downloaded")//每天客户端下载量
	private int downloaded;
	@Column(name="time")//时间 2005-05-05
	private String time;
	@Column(name="os")// 来自的系统
	private String os;
	@Column(name="province")// 来自的的省
	private String province;
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getVored() {
		return vored;
	}
	public void setVored(int vored) {
		this.vored = vored;
	}
	public int getUsersaved() {
		return usersaved;
	}
	public void setUsersaved(int usersaved) {
		this.usersaved = usersaved;
	}
	public int getUserlogined() {
		return userlogined;
	}
	public void setUserlogined(int userlogined) {
		this.userlogined = userlogined;
	}
	public int getUsersyned() {
		return usersyned;
	}
	public void setUsersyned(int usersyned) {
		this.usersyned = usersyned;
	}
	public int getDownloaded() {
		return downloaded;
	}
	public void setDownloaded(int downloaded) {
		this.downloaded = downloaded;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
