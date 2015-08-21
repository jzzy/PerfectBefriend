package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@SuppressWarnings("all")
@Entity
@Table(name = "groupchat")
public class GroupChat implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Column(name = "groupid")
	@Expose
	private String groupid;// 环信id
	@Column(name = "type")
	@Expose
	private int type;// 群类别
	
	@Column(name = "classgroup")
	@Expose
	private int classgroup;// 1群 2班级
	@Column(name = "groupno")
	@Expose
	private int groupno;// 群号
	@Column(name = "joincondition")
	@Expose
	private int joincondition;// 1 允许任何人 2 需要验证信息 3不允许任何人
	@Column(name = "name")
	@Expose
	private String name;// 群名字
	@Column(name = "area")
	@Expose
	private String area;// 省
	@Column(name = "areas")
	@Expose
	private String areas;// 市
	@Column(name = "userid")
	@Expose
	private int userid;// 用户id
	@Column(name = "img")
	@Expose
	private String img;// 头像
	@Column(name = "schoolname")
	@Expose
	private String schoolname;// 学校名字
	@Column(name = "schooladdress")
	@Expose
	private String schooladdress;// 学校地址
	@Column(name = "grade")
	@Expose
	private String grade;// 年级
	@Column(name = "gclass")
	@Expose
	private String gclass;// 班级
	@Column(name = "gclassintroduction")
	@Expose
	private String gclassintroduction;// 班级简介
	@Column(name = "headteachername")
	@Expose
	private String headteachername;// 班主任名字
	@Column(name = "phone")
	@Expose
	private String phone;// 手机号
	@Column(name = "time")
	@Expose
	private String time;// 时间

	public int getClassgroup() {
		return classgroup;
	}

	public void setClassgroup(int classgroup) {
		this.classgroup = classgroup;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getGclassintroduction() {
		return gclassintroduction;
	}

	public void setGclassintroduction(String gclassintroduction) {
		this.gclassintroduction = gclassintroduction;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public int getJoincondition() {
		return joincondition;
	}

	public void setJoincondition(int joincondition) {
		this.joincondition = joincondition;
	}

	public int getGroupno() {
		return groupno;
	}

	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
