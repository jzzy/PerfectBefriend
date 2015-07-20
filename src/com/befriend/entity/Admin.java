package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin implements Serializable {
    	/**
	 * 管理员
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="admin")//管理员名
	private String admin;
	@Column(name="pwd")//管理员密码
	private String pwd;
	@Column(name="level")//管理员级别
	private int level;
	public Integer getId() {
	    return id;
	}
	public void setId(Integer id) {
	    this.id = id;
	}
	public String getAdmin() {
	    return admin;
	}
	public void setAdmin(String admin) {
	    this.admin = admin;
	}
	public String getPwd() {
	    return pwd;
	}
	public void setPwd(String pwd) {
	    this.pwd = pwd;
	}
	public int getLevel() {
	    return level;
	}
	public void setLevel(int level) {
	    this.level = level;
	}
	
	
}
