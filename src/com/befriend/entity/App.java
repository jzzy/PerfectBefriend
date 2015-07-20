package com.befriend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app")
public class App {


	/**
	 * app类
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name")//app名字
	private String name;
	@Column(name="summary")//app概要
	private String summary;
	
	@Column(name="time")//app上传时间
	private String time;
	
	@Column(name="pathapk")//appapk上传地址
	private String pathapk;
	@Column(name="pathimg")//applogo图片地址
	private String pathimg;
	@Column(name="pathimg1")//应用截图1地址
	private String pathimg1;
	@Column(name="pathimg2")//应用截图2地址
	private String pathimg2;
	@Column(name="pathimg3")//应用截图3地址
	private String pathimg3;
	@Column(name="sequence")//app排序
	private int sequence;
	@Column(name="type")//app类别
	private String type;
	@Column(name="vnum")//app版本号
	private String vnum;
	@Column(name="dpt")//应用描述
	private String dpt;
	
	@Column(name="downloads")//app下载次数
	private int downloads;
	@Column(name="realds")//app真实下载次数
	private int realds;
	@Column(name="appsize")//app大小MB
	
	private double appsize;

	public double getAppsize() {
		return appsize;
	}

	public void setAppsize(double appsize) {
		this.appsize = appsize;
	}

	public int getRealds() {
		return realds;
	}

	public void setRealds(int realds) {
		this.realds = realds;
	}

	public String getPathimg1() {
		return pathimg1;
	}

	public void setPathimg1(String pathimg1) {
		this.pathimg1 = pathimg1;
	}

	public String getPathimg2() {
		return pathimg2;
	}

	public void setPathimg2(String pathimg2) {
		this.pathimg2 = pathimg2;
	}

	public String getPathimg3() {
		return pathimg3;
	}

	public void setPathimg3(String pathimg3) {
		this.pathimg3 = pathimg3;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVnum() {
		return vnum;
	}

	public void setVnum(String vnum) {
		this.vnum = vnum;
	}



	public String getDpt() {
		return dpt;
	}

	public void setDpt(String dpt) {
		this.dpt = dpt;
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

	public String getSummary() {
	    return summary;
	}

	public void setSummary(String summary) {
	    this.summary = summary;
	}

	public String getTime() {
	    return time;
	}

	public void setTime(String time) {
	    this.time = time;
	}

	public String getPathapk() {
	    return pathapk;
	}

	public void setPathapk(String pathapk) {
	    this.pathapk = pathapk;
	}

	public String getPathimg() {
	    return pathimg;
	}

	public void setPathimg(String pathimg) {
	    this.pathimg = pathimg;
	}

	public int getSequence() {
	    return sequence;
	}

	public void setSequence(int sequence) {
	    this.sequence = sequence;
	}

	public int getDownloads() {
	    return downloads;
	}

	public void setDownloads(int downloads) {
	    this.downloads = downloads;
	}

	
	
}
