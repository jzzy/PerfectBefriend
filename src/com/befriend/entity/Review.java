package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class Review implements Serializable
{
	/**
	 * 新闻评论
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="userid")//用户名
	private Integer userid;
	@Column(name="newsid")//新闻id
	private int newsid;
	@Column(name="review")//评论内容
	private String review;
	@Column(name="time")//评论时间
	private String time;
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
