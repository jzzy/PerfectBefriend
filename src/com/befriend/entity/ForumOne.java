package com.befriend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//论坛概要表
@Entity
@Table(name = "forumone")
public class ForumOne {

	/**
	 * 论坛概要表 one
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "title")
	// 论坛标题
	private String title;

	@Column(name = "type")
	// 论坛类别 1 专家答疑 2学前 3小学 4中学 5按地区分
	private int type;
	@Column(name = "total")
	// 论坛总回复数
	private int total;

	@Column(name = "userid")
	// 论坛创建者
	private int userid;
	@Column(name = "time")
	// 论坛创建时间
	private String time;

	@Column(name = "content")
	// 论坛内容
	private String content;

	@Column(name = "area")
	// 论坛地区省级
	private String area;
	@Column(name = "areas")
	// 论坛地区市级
	private String areas;
	@Column(name = "img")
	// 论坛图片
	private String img;
	@Column(name = "follectnum")
	// 论坛被收藏次数
	private int follectnum;
	@Column(name = "fHits")
	// 论坛被点击次数
	private int fHits;

	@Column(name = "frs")
	// 论坛被回复次数
	private int frs;



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
