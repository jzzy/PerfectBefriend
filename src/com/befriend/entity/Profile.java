package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="profile")
public class Profile  implements Serializable{
	/**
	 * Ⱥ��������
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="userid")
	private int userid;//�û�id
	@Column(name="groupid")
	private int groupid;//Ⱥid
	/**
	 * �ҳ�����
	 */
	@Column(name="sdname")
	private String sdname;//ѧ������
	
	@Column(name="kip")
	private String kip;//������ϵ
	/**
	 * ����
	 */
	@Column(name="name")
	private String name;//����
	@Column(name="phone")//�绰
	private String phone;

	@Column(name="ddb")//��Ϣ����� 0�ǽ���  1�ǲ�����
	private int ddb;
	@Column(name="judge")// 0�Ǽҳ�  1����ʦ
	private int judge;
	/**
	 * ��ʦ����
	 */
	
	@Column(name="rsbs")//�����Ŀ
	private String rsbs;
	/**
	 * ��Ⱥʱ��
	 */
	private String time;
	
	
	
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getSdname() {
		return sdname;
	}
	public void setSdname(String sdname) {
		this.sdname = sdname;
	}
	
	public String getKip() {
		return kip;
	}
	public void setKip(String kip) {
		this.kip = kip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getDdb() {
		return ddb;
	}
	public void setDdb(int ddb) {
		this.ddb = ddb;
	}
	public int getJudge() {
		return judge;
	}
	public void setJudge(int judge) {
		this.judge = judge;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRsbs() {
		return rsbs;
	}
	public void setRsbs(String rsbs) {
		this.rsbs = rsbs;
	}
	
	
	
	
}
