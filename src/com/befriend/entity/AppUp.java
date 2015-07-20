package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="appup")
public class AppUp implements Serializable
{

	/**
	 * 更新app
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="apptv")//版本号
	private int apptv;
	@Column(name="time")//最后更新时间
	private String time;
	@Column(name="path")//安装包地址
	private String path;
	
	@Column(name="updates")//更新内容
	private String updates;
	@Column(name="upnum")//有多少人  选择了升级
	private int upnum;
	
	public int getUpnum() {
		return upnum;
	}
	public void setUpnum(int upnum) {
		this.upnum = upnum;
	}
	public String getUpdates()
	{
		return updates;
	}
	public void setUpdates(String updates)
	{
		this.updates = updates;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public int getApptv()
	{
		return apptv;
	}
	public void setApptv(int apptv)
	{
		this.apptv = apptv;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	
	

}
