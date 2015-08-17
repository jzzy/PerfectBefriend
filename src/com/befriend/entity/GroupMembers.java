package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "groupmembers")
public class GroupMembers implements Serializable {
	/**
	 * Ⱥ��Ա ��ϵ��
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "userid")
	// �û�id
	private int userid;
	@Column(name = "groupid")
	// Ⱥid
	private int groupid;
	@Column(name = "urp")
	// �û� ��Ⱥ�Ĺ�ϵ 0�ȴ���� 1 ��Ⱥ��Ա 2 ��Ⱥ�� ������û� 3��Ⱥ��
	private int urp;
	@Column(name = "time")
	// ʱ��
	private String time;

	@Column(name = "message")
	// ��֤��Ϣ
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getUrp() {
		return urp;
	}

	public void setUrp(int urp) {
		this.urp = urp;
	}

}
