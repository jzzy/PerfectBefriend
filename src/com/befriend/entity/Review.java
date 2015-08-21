package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="review")
public class Review implements Serializable
{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Column(name="userid")
	@Expose
	private Integer userid;
	@Column(name="newsid")
	@Expose
	private int newsid;
	@Column(name="supports")
	@Expose
	private int supports;
	@Column(name="review")
	@Expose
	private String review;
	@Column(name="time")
	@Expose
	private String time;
	@Transient
	@Expose
	private Boolean b;
	
	public Boolean getB() {
		return b;
	}
	public void setB(Boolean b) {
		this.b = b;
	}
	public int getSupports() {
		return supports;
	}
	public void setSupports(int supports) {
		this.supports = supports;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	

	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public int getNewsid()
	{
		return newsid;
	}
	public void setNewsid(int newsid)
	{
		this.newsid = newsid;
	}
	public String getReview()
	{
		return review;
	}
	public void setReview(String review)
	{
		this.review = review;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	

}
