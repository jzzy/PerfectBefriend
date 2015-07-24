package com.befriend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="behavior")
public class Behavior
{
	public static final int NEWS_TYPE = 1;
	public static final int LABEL = 2;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private Integer id;
	@Column(name="user_id",nullable=false)
	private Integer userId;
	@Column(name="keyword",nullable=false)
	private String keyword;
	@Column(name="type",nullable=false)
	private int type;//关键词类型，1：新闻类别；2：新闻标签
	@Column(name="count",nullable=false)
	private long count;
	private int occupy;

	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getUserId()
	{
		return userId;
	}
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	public String getKeyword()
	{
		return keyword;
	}
	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}
	public long getCount()
	{
		return count;
	}
	public void setCount(long count)
	{
		this.count = count;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public int getOccupy()
	{
		return occupy;
	}
	public void setOccupy(int occupy)
	{
		this.occupy = occupy;
	}

}
