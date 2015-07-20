package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="collect")

public class Collect implements Serializable
{
	/**
	 * 收藏新闻
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="userid")//用户id
	private int userid;
	@Column(name="newsid")//新闻id
	private int newsid;
	@Column(name="newstitle")//新闻标题
	private String newstitle;
	@Column(name="time")//收藏时间
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
	public String getNewstitle() {
	    return newstitle;
	}
	public void setNewstitle(String newstitle) {
	    this.newstitle = newstitle;
	}
	public String getTime() {
	    return time;
	}
	public void setTime(String time) {
	    this.time = time;
	}
	
	

}
