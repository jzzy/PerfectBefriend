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
	/**
	 * �����ŵ���
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="userid")//�û�id
	private int userid;
	@Column(name="newsid")//����id
	private int newsid;
	
	@Column(name="time")//����ʱ��
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
	public int getNewsid() {
	    return newsid;
	}
	public void setNewsid(int newsid) {
	    this.newsid = newsid;
	}
	
	public String getTime() {
	    return time;
	}
	public void setTime(String time) {
	    this.time = time;
	}
	
	

}