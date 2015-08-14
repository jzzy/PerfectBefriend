package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@SuppressWarnings("all")
@Entity
@Table(name = "forumtwo")
public class ForumTwo  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "userid")

    private int userid;
    @Column(name = "reply")

    private String reply;
    @Column(name = "time")
  
    private String time;
    @Column(name = "forumid")
 
    private int forumid;
    @Column(name = "touserid")
   
    private int touserid;
    @Column(name = "supports")
    private Integer supports;
    @Column(name = "total")
	private int total;
    @Transient
    private Boolean b=false;
    

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Boolean getB() {
		return b;
	}

	public void setB(Boolean b) {
		this.b = b;
	}

	public Integer getSupports() {
		return supports;
	}

	public void setSupports(Integer supports) {
		this.supports = supports;
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

    public int getForumid() {
	return forumid;
    }

    public void setForumid(int forumid) {
	this.forumid = forumid;
    }

    public int getTouserid() {
	return touserid;
    }

    public void setTouserid(int touserid) {
	this.touserid = touserid;
    }

   

}
