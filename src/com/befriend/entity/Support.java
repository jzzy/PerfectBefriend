package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="support")

public class Support implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="userid")
	private int userid;
	@Column(name="objectid")
	private int objectid;
	@Column(name="comefrom")//1 news 2 forum
	private int comefrom;
	@Column(name="time")
	private String time;
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
	
	
	public int getObjectid() {
		return objectid;
	}
	public void setObjectid(int objectid) {
		this.objectid = objectid;
	}
	public int getComefrom() {
		return comefrom;
	}
	public void setComefrom(int comefrom) {
		this.comefrom = comefrom;
	}
	public String getTime() {
	    return time;
	}
	public void setTime(String time) {
	    this.time = time;
	}
	
	

}
