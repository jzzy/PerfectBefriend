package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cis")
/**
 * Ⱥ������Ϣ��
 */
public class Cis implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "senduserid")
	/**
	 * ����Ϣ���û�id
	 */
	private int senduserid;
	@Column(name = "groupid")
	/**
	 * Ⱥid
	 */
	private int groupid;
	@Column(name = "information")
	/**
	 * ���͵� ��Ϣ
	 */
	private String information;
	@Column(name = "time")
	/**
	 * ���͵� ʱ��
	 */
	private String time;
	@Column(name = "userid")
	/**
	 * �ҵ��û�id ����ҵ�ʱ û���ܵ� �ͻ���ҵ�  �û�id  ������ ���յ��Ժ� ��ɾ����
	 */
	private int userid;
	@Column(name = "online")
	/**
	 * 0δ�� 1�Ѷ�
	 */
	private Integer online;
	@Column(name = "ip")
	/**
	 * �ͻ���ip
	 */
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSenduserid() {
		return senduserid;
	}

	public void setSenduserid(int senduserid) {
		this.senduserid = senduserid;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
