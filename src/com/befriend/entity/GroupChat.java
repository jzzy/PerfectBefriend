package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="groupchat")
public class GroupChat implements Serializable{
  	/**
	 * 群概要表
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="groupno")//群号
	private int groupno;
	@Column(name="groupid")//环信 的群id
	private String groupid;
	@Column(name="name")//群名称
	private String name;
	@Column(name="userid")//群创建者
	private int userid;
	@Column(name="img")//群图标
	private String img;
	@Column(name="schoolname")//学校名称
	private String schoolname;
	@Column(name="schooladdress")//学校地址
	private String schooladdress;
	@Column(name="grade")//年级
	private String grade;
	@Column(name="gclass")//班级
	private String gclass;
	@Column(name="headteachername")//班主任姓名
	private String headteachername;
	@Column(name="htphone")//班主任电话
	private String htphone;
	@Column(name="time")//群名称
	private String time;
	

	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getGroupno() {
		return groupno;
	}
	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public String getSchooladdress() {
		return schooladdress;
	}
	public void setSchooladdress(String schooladdress) {
		this.schooladdress = schooladdress;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGclass() {
		return gclass;
	}
	public void setGclass(String gclass) {
		this.gclass = gclass;
	}
	public String getHeadteachername() {
		return headteachername;
	}
	public void setHeadteachername(String headteachername) {
		this.headteachername = headteachername;
	}
	public String getHtphone() {
		return htphone;
	}
	public void setHtphone(String htphone) {
		this.htphone = htphone;
	}

	
	
}
