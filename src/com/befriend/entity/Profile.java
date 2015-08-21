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
@Table(name="profile")
public class Profile  implements Serializable{
	/**
	 * 群个人资料
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private Integer id;
	
	@Column(name="userid")
	@Expose
	private int userid;//用户id
	@Column(name="groupid")
	@Expose
	private int groupid;//群id
	/**
	 * 家长独有
	 */
	@Column(name="sdname")
	@Expose
	private String sdname;//学生姓名
	
	@Column(name="kip")
	@Expose
	private String kip;//亲属关系
	/**
	 * 共有
	 */
	@Column(name="name")
	@Expose
	private String name;//姓名
	@Column(name="phone")//电话
	@Expose
	private String phone;

	@Column(name="ddb")//消息免打扰 0是接收  1是不接收
	@Expose
	private int ddb;
	@Column(name="judge")// 0是家长  1是老师
	@Expose
	private int judge;
	/**
	 * 老师独有
	 */
	
	@Column(name="rsbs")//负责科目
	@Expose
	private String rsbs;
	/**
	 * 进群时间
	 */
	@Column(name="time")
	@Expose
	private String time;
	
	
	
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getSdname() {
		return sdname;
	}
	public void setSdname(String sdname) {
		this.sdname = sdname;
	}
	
	public String getKip() {
		return kip;
	}
	public void setKip(String kip) {
		this.kip = kip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getDdb() {
		return ddb;
	}
	public void setDdb(int ddb) {
		this.ddb = ddb;
	}
	public int getJudge() {
		return judge;
	}
	public void setJudge(int judge) {
		this.judge = judge;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRsbs() {
		return rsbs;
	}
	public void setRsbs(String rsbs) {
		this.rsbs = rsbs;
	}
	
	
	
	
}
