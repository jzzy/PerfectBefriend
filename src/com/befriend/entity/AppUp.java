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
@Table(name="appup")
public class AppUp implements Serializable
{

	/**
	 * ����app
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Column(name="apptv")//�汾��
	@Expose
	private int apptv;
	@Column(name="time")//������ʱ��
	@Expose
	private String time;
	@Column(name="path")//��
	@Expose
	private String path;
	
	@Column(name="updates")//��������
	@Expose
	private String updates;
	@Column(name="upnum")//�ж�����  ѡ��������
	@Expose
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
