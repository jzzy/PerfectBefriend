package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stas")
public class Stas  implements Serializable {
	/**
	 * ÿ���¼  ͳ��
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="vored")//ÿ�������ο�����
	private int vored;
	@Column(name="usersaved")//ÿ�������û�����
	private int usersaved;
	@Column(name="userlogined")//ÿ������û�����
	private int userlogined;
	@Column(name="usersyned")//ÿ��ͬʱ����(�۷�)�û�����
	private int usersyned;
	@Column(name="downloaded")//ÿ��ͻ���������
	private int downloaded;
	@Column(name="time")//ʱ�� 2005-05-05
	private String time;
	@Column(name="os")// ���Ե�ϵͳ
	private String os;
	@Column(name="province")// ���Եĵ�ʡ
	private String province;
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getVored() {
		return vored;
	}
	public void setVored(int vored) {
		this.vored = vored;
	}
	public int getUsersaved() {
		return usersaved;
	}
	public void setUsersaved(int usersaved) {
		this.usersaved = usersaved;
	}
	public int getUserlogined() {
		return userlogined;
	}
	public void setUserlogined(int userlogined) {
		this.userlogined = userlogined;
	}
	public int getUsersyned() {
		return usersyned;
	}
	public void setUsersyned(int usersyned) {
		this.usersyned = usersyned;
	}
	public int getDownloaded() {
		return downloaded;
	}
	public void setDownloaded(int downloaded) {
		this.downloaded = downloaded;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
