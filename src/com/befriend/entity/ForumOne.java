package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "forumone")
public class ForumOne  implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "title")
	
	private String title;//标题

	@Column(name = "type")
	
	
	private int type;//类型

	@Column(name = "total")
	
	private int total;//回复次数

	@Column(name = "userid")
	
	private int userid;//用户id
	@Column(name = "time")
	
	private String time;//创建时间

	@Column(name = "content")
	
	private String content;

	@Column(name = "area")//省
	
	private String area;
	@Column(name = "areas")
	
	private String areas;//市
	@Column(name = "img")
	
	private String img;//图片
	@Column(name = "follectnum")
	
	private int follectnum;//被收藏次数
	@Column(name = "fHits")
	
	private int fHits;//点击次数

	@Column(name = "frs")
	
	private int frs;//论坛总回复数
	@Column(name="supports")
	private int supports;//点赞总数
	@Transient
	private Boolean b=false;// 用户 是否赞过
	

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}



	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getfHits() {
		return fHits;
	}

	public void setfHits(int fHits) {
		this.fHits = fHits;
	}

	public int getFrs() {
		return frs;
	}

	public void setFrs(int frs) {
		this.frs = frs;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getFollectnum() {
		return follectnum;
	}

	public void setFollectnum(int follectnum) {
		this.follectnum = follectnum;
	}

}
