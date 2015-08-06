package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;


@Entity
@Table(name = "forum_twotype")
public class ForumTwoType  implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Column(name = "title")
	@Expose
	private String title;
	@Column(name = "adminname")
	@Expose
	private String adminname;
	@Column(name = "time")
	@Expose
	private String time;

	@Column(name = "img")
	@Expose
	private String img;

	@Column(name="supports")
	//被赞次数
	@Expose
	private int supports;
	/**
	 * 设置 多对一关系
	 */
	@ManyToOne(cascade=CascadeType.ALL)        
	@JoinColumn(name="fotid") //这是数据库里面的，这个字段是关联外键
	@Expose(serialize=false)
	private ForumOneType FOT;//与这里对应，这里表示一
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getSupports() {
		return supports;
	}
	public void setSupports(int supports) {
		this.supports = supports;
	}
	public ForumOneType getFOT() {
		return FOT;
	}
	public void setFOT(ForumOneType fOT) {
		FOT = fOT;
	}
	
	

	
}
