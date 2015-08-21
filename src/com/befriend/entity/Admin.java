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
@Table(name="admin")
public class Admin implements Serializable {
    	/**
	 * ����Ա
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Column(name="admin")//����Ա��
	@Expose
	private String admin;
	@Column(name="pwd")//����Ա����
	@Expose
	private String pwd;
	@Column(name="level")//����Ա����
	@Expose
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
