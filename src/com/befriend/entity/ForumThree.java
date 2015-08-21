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
@Table(name="forumthree")
public class ForumThree  implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Column(name="userid")
	@Expose
	private int userid;
	@Column(name="touserid")
	@Expose
	private int touserid;
	@Column(name="reply")
	@Expose
	private String reply;
	@Column(name="time")
	@Expose
	private String time;
    @Column(name = "forumid")
    @Expose
	private int forumid;
    @Column(name = "forumtwoid")
    @Expose
    private int forumtwoid;
    @Expose
    private Integer supports;
	
	
	public Integer getSupports() {
		return supports;
	}
	public void setSupports(Integer supports) {
		this.supports = supports;
	}
	public int getForumtwoid() {
		return forumtwoid;
	}
	public void setForumtwoid(int forumtwoid) {
		this.forumtwoid = forumtwoid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public int getTouserid() {
		return touserid;
	}
	public void setTouserid(int touserid) {
		this.touserid = touserid;
	}
	public int getForumid() {
		return forumid;
	}
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}
	
}
	
