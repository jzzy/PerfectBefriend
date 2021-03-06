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
@Table(name="behavior")
public class Behavior implements Serializable
{
	private static final long serialVersionUID = 1L;
	public static final int NEWS_TYPE = 1;
	public static final int LABEL = 2;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	@Expose
	private Integer id;
	@Column(name="user_id",nullable=false)
	@Expose
	private Integer userId;
	@Column(name="keyword",nullable=false)
	@Expose
	private String keyword;
	@Column(name="type",nullable=false)
	@Expose
	private int type;
	@Column(name="count",nullable=false)
	@Expose
	private long count;
	@Transient
	@Expose
	private int occupy;
	@Expose
	@Transient
	private double weight;

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
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}
